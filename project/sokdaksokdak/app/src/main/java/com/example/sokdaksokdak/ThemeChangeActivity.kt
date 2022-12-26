package com.example.sokdaksokdak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sokdaksokdak.Factory.CloverThemeFactory
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.Factory.PolaThemeFactory
import com.example.sokdaksokdak.databinding.ActivityThemeChangeBinding

class ThemeChangeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityThemeChangeBinding
    private var currentTheme = R.style.AppPolaTheme
    private lateinit var pref: DefaultPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        pref= DefaultPreferenceManager(this)

        val theme = pref.getThemeType()
        currentTheme = getAppTheme(theme)
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityThemeChangeBinding.inflate(layoutInflater)

        // polaroid 테마 클릭시 폴라로이드 테마 팩토리 실행
        binding.polaThemeBtn.setOnClickListener{
            pref.setThemeType("pola_theme")
            PolaThemeFactory().createNaviActivity(this)
            PolaThemeFactory().createSplashActivity(this)
        }

        // clover 테마 클릭시 클로버 테마 팩토리 실행
        binding.cloverThemeBtn.setOnClickListener {
            pref.setThemeType("clover_theme")
            CloverThemeFactory().createNaviActivity(this)
            CloverThemeFactory().createSplashActivity(this)
        }


        // 네비게이션 바 클릭시 해당 액티비티로 이동
        binding.navigationView.setOnItemSelectedListener { item->
            val intent = Intent(this, PolaNaviActivity::class.java)
            when(item.itemId){
                R.id.diaryFragment->{
                    intent.putExtra("frag",0)
                    startActivity(intent)
                    true
                }
                R.id.mypageFragment->{
                    intent.putExtra("frag",1)
                    startActivity(intent)
                    true
                }
                else -> false
            }

        }

        setContentView(binding.root)
    }
    override fun onResume() {

        val themeType = pref.getThemeType()
        val settingTheme = getAppTheme(themeType)

        if (currentTheme != settingTheme) {
            recreate()
        }
        super.onResume()
    }

    fun getAppTheme(theme: String?): Int {
        var newTheme: Int
        when (theme) {
            "pola_theme" -> newTheme = R.style.AppPolaTheme
            "clover_theme" -> newTheme = R.style.AppCloverTheme
            else -> newTheme = R.style.AppPolaTheme
        }
        return newTheme

    }
}