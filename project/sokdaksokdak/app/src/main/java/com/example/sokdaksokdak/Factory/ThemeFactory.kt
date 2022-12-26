package com.example.sokdaksokdak.Factory

import android.content.Context
import com.example.sokdaksokdak.writeDiary.DiaryFragment
import com.example.sokdaksokdak.MypageFragment

interface ThemeFactory{
    // 테마 팩토리 인터페이스
    fun createNaviActivity(context: Context)
    fun createSplashActivity(context: Context)

}