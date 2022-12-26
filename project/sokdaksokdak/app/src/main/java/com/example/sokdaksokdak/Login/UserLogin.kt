package com.example.sokdaksokdak.Login

import android.content.Context
//전략패턴에서 로그인을 사용하는 객체를 위한 클래스
class UserLogin(
    private var socialLogin: SocialLogin
) {
    fun login(context: Context?) {
        socialLogin.Login(context)
    }
}