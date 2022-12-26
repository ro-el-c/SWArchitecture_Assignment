package com.example.sokdaksokdak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.Login.LoginFragment
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.databinding.ActivityPolaNaviBinding
import com.example.sokdaksokdak.writeDiary.DiaryFragment
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient


private const val TAG_DIARY = "diary_fragment"
private const val TAG_LOGIN = "login_fragment"
private const val TAG_MY_PAGE = "mypage_fragment"

class PolaNaviActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPolaNaviBinding
    var currentTheme = R.style.AppPolaTheme
    private lateinit var pref: DefaultPreferenceManager
    var auth : FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pref = DefaultPreferenceManager(this)

        // shared preference에서 사용자가 설정한 테마 가져옴
        val theme = pref.getThemeType()
        currentTheme = getAppTheme(theme)
        // setTheme == 액티비티의 테마 설정하는 내장 함수
        setTheme(currentTheme)

        binding = ActivityPolaNaviBinding.inflate(layoutInflater)


        //구글 계정 가져옴(없으면 currentUser == null)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth?.currentUser

        //카카오 로그인 확인 및 카카오 구글 둘 다 로그인 안되어있으면 로그인페이지, 아니면 일기작성 페이지로 이동
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d("로그인 화면", "아직 카카오 로그인 안함")
                if(currentUser != null){
                    Log.d("로그인 화면", "이미 구글 로그인되어있음")
                    setFragment(TAG_DIARY,DiaryFragment())
                    binding.navigationView.visibility = View.VISIBLE
                }else{
//                    binding.navigationView.visibility = View.GONE
                    Log.d("로그인 화면", "아직 구글 로그인 안함")
//                    setFragment(TAG_LOGIN, LoginFragment())
                }
            } else if (tokenInfo != null) {
                Log.d("로그인 화면", "이미 카카오 로그인되어있음")
                setFragment(TAG_DIARY,DiaryFragment())
                binding.navigationView.visibility = View.VISIBLE
            }
        }
        binding.navigationView.visibility = View.VISIBLE

        // 테마 변경시 intent에서 받은 int값 확인해 fragement 연결 (0: 다이어리, 1: 설정페이지)
        when(intent.getIntExtra("frag",0)){
            0->{
                setFragment(TAG_DIARY, DiaryFragment())
                true
            }
            1->{
                setFragment(TAG_MY_PAGE,MypageFragment())
                true
            }
            else -> false
        }


        // 네비게이션 바에 when 구문 통해 fragment 연결
        // 하단의 네비게이션에서 다이어리 클릭시 다이어리 화면띄우고, 설정 클릭시 설정화면 띄움
        binding.navigationView.setOnItemSelectedListener { item->
            // 메뉴 선택시 해당 화면 setting
            when(item.itemId){
                R.id.diaryFragment->{
                    setFragment(TAG_DIARY, DiaryFragment())
                    true
                }
                R.id.mypageFragment->{
                    setFragment(TAG_MY_PAGE,MypageFragment())

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


    // fragment setting 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }


        val diary = manager.findFragmentByTag(TAG_DIARY)
        val mypage = manager.findFragmentByTag(TAG_MY_PAGE)
        val login = manager.findFragmentByTag(TAG_LOGIN)


        if (diary != null) {
            fragTransaction.hide(diary)
        }

        if (mypage != null) {
            fragTransaction.hide(mypage)
        }

         if (tag == TAG_DIARY) {
            if (diary != null) {
                fragTransaction.show(diary)
            }
        } else if (tag == TAG_MY_PAGE) {
            if (mypage != null) {
                fragTransaction.show(mypage)
            }
        } else if (tag == TAG_LOGIN) {
            if (login != null) {
                fragTransaction.show(login)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }

}

