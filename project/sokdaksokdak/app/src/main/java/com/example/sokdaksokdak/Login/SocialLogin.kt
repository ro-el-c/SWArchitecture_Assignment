package com.example.sokdaksokdak.Login

import android.content.Context
//전략 패턴을 위한 sociallogin 인터페이스
interface SocialLogin {
    fun Login(context: Context?)
}