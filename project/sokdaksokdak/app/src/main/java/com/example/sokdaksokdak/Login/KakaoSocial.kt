package com.example.sokdaksokdak.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sokdaksokdak.PolaNaviActivity
import com.example.sokdaksokdak.databinding.FragmentLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
//카카오 로그인 알고리즘을 수행하는 클래스
class KakaoSocial : AppCompatActivity(), SocialLogin {
    lateinit var name: String
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = FragmentLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun Login(context: Context?) { // login fragment의 context를 사용
        if(context != null){
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("실패1", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("성공", "카카오계정으로 로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.me { user, error ->
                        if (user != null) {
                            name = user.kakaoAccount?.profile?.nickname.toString()
                        }
                    }
                    //로그인이 성공하면 화면을 전환한다
                    var intent = Intent(context, PolaNaviActivity::class.java)
                    context.startActivity(intent)
                }
            }
            // kakao login에는 카카오톡, 카카오계정 로그인 두가지
            // 카카오톡 설치되어 있으면 이를 우선으로
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e("실패2", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i("성공", "카카오톡으로 로그인 성공 ${token.accessToken}")
                        UserApiClient.instance.me { user, error ->
                            if (user != null) {
                                name = user.kakaoAccount?.profile?.nickname.toString()
                                return@me
                            }
                        }
                        var intent = Intent(context, PolaNaviActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }
}
