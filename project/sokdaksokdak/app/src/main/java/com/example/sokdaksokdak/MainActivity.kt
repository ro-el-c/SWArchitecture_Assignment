package com.example.sokdaksokdak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sokdaksokdak.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //MainActivity를 두고 그 위에 Fragment를 씌운다음 이들을 네비게이션바로 이동하는 구조
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var keyHash = Utility.getKeyHash(this)
        Log.d("Hash",keyHash)

        binding.button.setOnClickListener {

            val intent = Intent(this, PolaSplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

    }

}


