package com.example.sokdaksokdak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.Diary
import com.example.sokdaksokdak.database.User
import com.example.sokdaksokdak.databinding.ActivityPolaSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Duration


class PolaSplashActivity : AppCompatActivity() {
    var currentTheme = R.style.AppPolaTheme
    private lateinit var pref: DefaultPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = DefaultPreferenceManager(this)

        // shared preference에서 사용자가 설정한 테마 가져옴
        val theme = pref.getThemeType()
        currentTheme = getAppTheme(theme)
        // setTheme == 액티비티의 테마 설정하는 내장 함수
        setTheme(currentTheme)

        setContentView(R.layout.activity_pola_splash)

        // SplashActivity 파일 생성 후 Duration 시간동안 띄움
        Handler().postDelayed({
            val intent = Intent(this, PolaNaviActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },Duration)

    }

    override fun onResume() {

        val themeType = pref.getThemeType()
        val settingTheme = getAppTheme(themeType)

        // 사용자가 설정한 테마와 시스템의 테마가 다르면 activity recreate
        if (currentTheme != settingTheme) {
            recreate()
        }
        super.onResume()
    }

    // 현재 shared preference에 어떤 테마가 기억되는지 가져옴
    fun getAppTheme(theme: String?): Int {
        var newTheme: Int
        when (theme) {
            "pola_theme" -> newTheme = R.style.AppPolaTheme
            "clover_theme" -> newTheme = R.style.AppCloverTheme
            else -> newTheme = R.style.AppPolaTheme
        }
        return newTheme

    }
    // splash 화면 최대 1초 띄우기
    companion object{
        private const val Duration:Long=1000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}