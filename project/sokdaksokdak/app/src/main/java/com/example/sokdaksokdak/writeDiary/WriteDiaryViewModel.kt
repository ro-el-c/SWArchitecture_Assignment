package com.example.sokdaksokdak.writeDiary

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.sokdaksokdak.database.AppDatabase

class WriteDiaryViewModel(application: Application): AndroidViewModel(application) {
    var recommendKeyword = RecommendKeyword()
    lateinit var date:String
    private var writeDiary = WriteDiary(application)

    // 오늘의 일기 데이터 생성
    public fun insertData() {
        writeDiary.insertDiary()
        println("successfully initialized diary data")
    }

    // 일기 작성 완료시 DB 업데이트
    public fun newDiaryData(keyword:String, content:String) {
        writeDiary.newDiaryData(keyword, content)
    }

    // 오늘의 일기 데이터 존재 여부 확인
    public fun checkDataExists(): Boolean {
        return writeDiary.isDataExists()
    }

    // 오늘의 일기 작성이 완료된 상태인지 확인
    public fun checkDiaryCompleted(): Boolean {
        val content = writeDiary.getDiaryContent()
        return if (content == "일기를 작성하세요."){
            false
        } else{
            Log.i("content", content)
            true
        }
    }

    /** showKeyword
     *
     * keyword 만 갱신 된 상태일 때
     *
     * 1. 오늘 날짜에 해당하는 keyword 가져오기
     * 2. 가져온 keyword 가
     *    2.1. 초기값일 때
     *         2.1.1. 사용자의 키워드 추천 여부 확인
     *                2.1.1.1. 추천 - random 으로 키워드 가져오기
     *                2.1.1.2. 비추천 - ""로 수정(초기값과의 차이를 주기 위함)
     *         2.1.1. 가져온 keyword DB 에 update
     *    2.2. 이미 갱신된 값일 때 - 바로 반환
     *
     * */
    public fun showKeyword(key:Boolean): String{
        // 1. 오늘 날짜에 해당하는 keyword 가져오기
        var keywordDB = writeDiary.getKeyword()

        Log.i("현재 저장 keyword: ", keywordDB)

        return if (keywordDB == "키워드를 선택하세요."){ // 2.1. 가져온 키워드가 초기값일 때
            if (key){ // 2.1.1. 키워드 추천 여부 확인 - SharedPreference 에서 사용자의 키워드 추천 여부 확인
                Log.i("keyword","Get Random Keyword")
                getRandomKeyword() // 2.1.1.1 추천 - random 값 가져오기
            }else{
                val temp = ""
                Log.i("keyword","Write your own Keyword")
                writeDiary.updateKeyword(temp) // 2.1.1.2 비추천 - ""로 수정(초기값과의 차이를 주기 위함)
                return temp
            }
        } else{ // 2.2. 이미 갱신된 값일 때 - 바로 반환
            return keywordDB
        }
    }

    // 오늘 날짜에 해당하는 일기의 내용 가져오기
    public fun showContent(): String{
        return writeDiary.getDiaryContent()
    }

    // 키워드 추천 여부가 true 일 때, 랜덤으로 키워드 가져오는 함수 호출
    private fun getRandomKeyword(): String {
        val keyword = recommendKeyword.randomKeyword()
        writeDiary.updateKeyword(keyword)
        return keyword
    }

    // 수정된 키워드 DB 에 업데이트
    fun setKeyword(keyword: String) {
        writeDiary.updateKeyword(keyword)
    }
}