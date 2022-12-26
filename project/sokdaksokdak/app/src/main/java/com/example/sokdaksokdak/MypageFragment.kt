package com.example.sokdaksokdak

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.databinding.FragmentMypageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient


class MypageFragment : Fragment() {
    var auth : FirebaseAuth?= null
    var googleSignInClient : GoogleSignInClient?= null
    lateinit var prefs: SharedPreferences
    private lateinit var pref: DefaultPreferenceManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMypageBinding.inflate(layoutInflater)
        binding.themeBtn.setOnClickListener {
            val intent = Intent(activity,ThemeChangeActivity::class.java)
            startActivity(intent)

        }

        // 현재 모드 화면에 출력
        pref = DefaultPreferenceManager(requireContext())
        when(pref.getThemeType()){
            "pola_theme" ->binding.currentThemeTextView.text="폴라로이드 테마"
            "clover_theme" ->binding.currentThemeTextView.text="네잎클로버 테마"
        }
        //유저 정보가 있는지 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                //로그인을 google로 했을 때
                var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("72536997949-mgi6or994u3rn0bfnseto9ucpnd2bdft.apps.googleusercontent.com")
                    .requestEmail()
                    .build()
                googleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }

                // firebaseauth를 사용하기 위한 인스턴스 get
                auth = FirebaseAuth.getInstance()
                // 마이페이지에 사용자 이름 띄움
                binding.mypageName.text = FirebaseAuth.getInstance().currentUser?.displayName
//                binding.mypageBirth.visibility = View.INVISIBLE
                // 구글 로그아웃
                binding.logoutBtn.setOnClickListener {
                    FirebaseAuth.getInstance().signOut()
                    googleSignInClient?.signOut()
                    Log.d("구글","로그아웃 성공")

                    var logoutIntent = Intent (context, PolaNaviActivity::class.java)
                    logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(logoutIntent)

                }
                //구글 탈퇴
                binding.secessionBtn.setOnClickListener{
                    FirebaseAuth.getInstance().signOut()
                    googleSignInClient?.revokeAccess()
                    auth!!.currentUser?.delete()
                    Log.d("구글","탈퇴 성공")

                    var logoutIntent = Intent (context, PolaNaviActivity::class.java)
                    logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(logoutIntent)
                }
            } else if(tokenInfo != null) {
                //로그인을 kakao로 했을 때
                UserApiClient.instance.me { user, error ->
                    if (user != null) {
                        //마이페이지에 유저 이름 띄움
                        var name = user.kakaoAccount?.profile?.nickname.toString()
                        var birth = user.kakaoAccount?.birthday.toString()
                        //var birthyear = user.kakaoAccount?.birthyear.toString()
                        Log.d("이름","이름 : "+name)
//                        binding.mypageBirth.visibility = View.VISIBLE
                        binding.mypageName.text = name
//                        binding.mypageBirth.text = birth
                    }
                }
                // 카카오 로그아웃
                binding.logoutBtn.setOnClickListener {
                    UserApiClient.instance.logout { error ->
                        if (error != null) {
                            Log.d("카카오", " 로그아웃 실패")
                        }else {
                            Log.d("카카오", "로그아웃 성공")
                        }
                        val intent = Intent(context, PolaNaviActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    }
                }
                //카카오 탈퇴
                binding.secessionBtn.setOnClickListener{
                    UserApiClient.instance.unlink { error ->
                        if (error != null) {
                            Log.d("카카오", "탈퇴 실패")
                        }else {
                            Log.d("카카오", " 탈퇴 성공")
                            //db 데이터 삭제
                            val intent = Intent(context, PolaNaviActivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        }
                    }
                }
            }
            //키워드 추천 여부를 사용 및 저장하기 위한 sharedpreferences사용
            val shared = requireActivity().getSharedPreferences("keyword", Context.MODE_PRIVATE)
            val key = shared.getBoolean("isKeyword",true)
            //저장되어 있는 값을 이용해서 추천여부 반영
            if(key){
                Log.d("keyword","true")
                binding.keywordToggleBtn.isChecked = true
            }else if(!key){
                Log.d("keyword","false")
                binding.keywordToggleBtn.isChecked = false
            }

            //추천여부를 사용자의 선택에 따라 sharedpreference에 값을 업데이트
            binding.keywordToggleBtn.setOnClickListener {
                val prefs = requireActivity().getSharedPreferences("keyword", Context.MODE_PRIVATE)
                val editor = prefs.edit()

                Log.i("toggleBtn", binding.keywordToggleBtn.isChecked.toString())

                if(binding.keywordToggleBtn.isChecked){
                    editor.putBoolean("isKeyword",true);
                    editor.apply()
                }else if(!binding.keywordToggleBtn.isChecked) {
                    editor.putBoolean("isKeyword", false);
                    editor.apply()
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}