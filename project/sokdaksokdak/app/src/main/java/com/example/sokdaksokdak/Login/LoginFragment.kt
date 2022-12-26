package com.example.sokdaksokdak.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sokdaksokdak.PolaNaviActivity


import com.example.sokdaksokdak.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

//실제 로그인이 실행되는 fragment
class LoginFragment : Fragment() {
    //kakao, google 클래스를 선언
    private lateinit var kakaoSocial: KakaoSocial
    private lateinit var googleSocial: GoogleSocial

    private lateinit var binding: FragmentLoginBinding
    //받아온 구글 사용자 정보를 이용해서 구글을 로그인을 하기 위한 변수
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val GOOGLE_REQUEST_CODE = 99
    val TAG = "googleLogin"
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.kakaoLoginButton.setOnClickListener{// 카카오 로그인을 수행
            //user객체가 kakaSocial을 이용하여 kakao로그인 실행
            kakaoSocial = KakaoSocial()
            val user = UserLogin(kakaoSocial)
            user.login(context)
        }

        binding.googleLoginButton.setOnClickListener {// 구글 로그인을 수행
            //user객체가 googlesocial을 이용하여 google로그인 실행
            googleSocial = GoogleSocial()
            val user = UserLogin(googleSocial)
            user.login(context)
            //객체를 통해 받아온 구글 사용자 정보를 이용하여 로그인 화면을 띄운다
            googleSignInClient = googleSocial.getClient()
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE)
        }
        return binding.root
    }
    //구글 로그인 화면에서 로그인이 성수이 성공적으로 됐는지 확인
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
                Log.i("google login",account.givenName + "구글 로그인 성공")
            } catch (e: ApiException) {
//                Toast.makeText(this, "구글 회원가입에 실패하였습니다: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.i("google login","구글 로그인 실패")
            }
        }
    }
    // 구글 로그인 화면을 띄우는 함수
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                toMainActivity(auth.currentUser)
            }
        }
    }
    //fragment가 실행되었을때 이미 로그인이 되어있다면 화면을 전환
    public override fun onStart() {
        super.onStart()
        //유저가 로그인되어있는지 확인
        val currentUser = auth.currentUser
        toMainActivity(currentUser)
    }

    //유저가 카카오 로그인하면 메인액티비티로 이동
    private fun moveMain() {
        startActivity(Intent(context, PolaNaviActivity::class.java))
    }
    //메인 화면으로 이동하기 위한 함
    private fun toMainActivity(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(context, PolaNaviActivity::class.java))
        }
    }

}




