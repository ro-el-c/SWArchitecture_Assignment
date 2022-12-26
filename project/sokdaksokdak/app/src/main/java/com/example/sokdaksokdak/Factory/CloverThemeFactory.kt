package com.example.sokdaksokdak.Factory

import android.content.Context
import android.content.Intent
import com.example.sokdaksokdak.*


class CloverThemeFactory() : ThemeFactory {
    // 클로버 테마 팩토리
    // navigation activity와 splash activity를 새로 시작
    // 이때, shared preference에는 클로버 테마가 저장되어 있음
    override fun createNaviActivity(context: Context) {
        val intent = Intent(context, PolaNaviActivity::class.java)
        context.startActivity(intent)
    }

    override fun createSplashActivity(context: Context){
        val intent = Intent(context, PolaSplashActivity::class.java)
        context.startActivity(intent)
    }

}