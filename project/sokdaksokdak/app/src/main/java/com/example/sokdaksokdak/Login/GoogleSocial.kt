package com.example.sokdaksokdak.Login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sokdaksokdak.PolaSplashActivity
import com.example.sokdaksokdak.R
import com.example.sokdaksokdak.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//google로그인 알고리즘을 수행하는 클래스
class GoogleSocial : AppCompatActivity(), SocialLogin {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var context : Context
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val GOOGLE_REQUEST_CODE = 99
    val TAG = "googleLogin"
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun Login(context: Context?) {
        if(context != null){
            this.context = context //login fragment의 context를 사용
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) // 구글 사용자 정보를 가져오기 위한 키
                .requestIdToken("72536997949-mgi6or994u3rn0bfnseto9ucpnd2bdft.apps.googleusercontent.com")
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(context,gso) // 구글 사용자 정보
            auth = FirebaseAuth.getInstance()
        }
    }

    fun getClient(): GoogleSignInClient {
        return this.googleSignInClient // 구글 사용자 정보를 반환한다
    }
}