package com.example.sokdaksokdak

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
//카카오 로그인 api를 사용하기 위한 클래스
class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "c7795b54e50244f372d84a1c2663a365")

    }
}