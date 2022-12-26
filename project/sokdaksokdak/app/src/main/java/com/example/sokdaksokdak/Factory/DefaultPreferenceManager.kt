package com.example.sokdaksokdak.Factory

import android.content.Context
import android.preference.PreferenceManager

class DefaultPreferenceManager(private val context:Context) {
    companion object{
        private const val themeType="themeType"
    }

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = sharedPreferences.edit()

    // shared preference 통해 어플리케이션 전반적으로 사용자가 설정한 테마 기억
    fun setThemeType(type:String){
        editor.putString(themeType,type).commit()
    }

    // 테마 get
    fun getThemeType()=sharedPreferences.getString(themeType,"pola_theme")
}