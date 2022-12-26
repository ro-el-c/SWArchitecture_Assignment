package com.example.sokdaksokdak.writeDiary

import android.app.Application

// 오늘의 일기 내용 생성 및 업데이트를 위한 클래스
class WriteDiary(application: Application) {
    private val repository = DiaryRepository(application)

    private lateinit var keyword: String
    private lateinit var content: String
    private val date: Int by lazy{
        20221218
    }

    // 생성자
    // 일기 기본 데이터
    init {
        this.keyword = "키워드를 선택하세요."
        this.content = "일기를 작성하세요."
    }

    // 오늘 날짜의 keyword 가져오기
    public fun getKeyword(): String{
        var keywordDB = repository.getTodayKeyword()
        // Diary Dao 이용
        return keywordDB
    }

    // 파라미터로 받은 keyword를 DB에 업데이트
    public fun updateKeyword(keyword:String){
        this.keyword = keyword
        updateDiary()
    }

    // 일기 작성 완료 눌렀을 때,
    // 일기 데이터 갱신 후, DB에 update 하기
    public fun newDiaryData(keyword:String, content:String){
        this.keyword = keyword
        this.content = content

        updateDiary()
    }

    // 오늘의 일기 데이터 생성
    public fun insertDiary(){
        repository.insertData("키워드를 선택하세요.", "일기를 작성하세요.")
    }

    // DB에 일기 업데이트
    // - keyword 만 혹은 keyword 와 content 업데이트
    public fun updateDiary(){
        repository.updateData(this.keyword, this.content)
    }

    // 오늘의 데이터 존재 여부 가져오기
    fun isDataExists(): Boolean {
        return repository.isDataExists()
    }

    // 오늘의 일기 내용 불러오기
    fun getDiaryContent(): String {
        return repository.getDiaryContent()
    }

    // 선택된 날짜의 일기 데이터 존재 여부 불러오기
    fun isDateDataExists(date:String):Boolean{
        return repository.isDateDataExists(date)
    }

    // 선택된 날짜의 일기 키워드 불러오기
     fun getDateKeyword(date:String): String{
        return repository.getDateKeyword(date)
    }

    // 선택된 날짜의 일기 내용 불러오기
    fun getDateDiaryContent(date:String):String{
        return repository.getDateDiaryContent(date)
    }
}