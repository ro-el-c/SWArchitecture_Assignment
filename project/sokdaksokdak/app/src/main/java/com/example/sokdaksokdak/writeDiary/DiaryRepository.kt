package com.example.sokdaksokdak.writeDiary

import android.app.Application
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.Diary

class DiaryRepository(application: Application) {
    private val database = AppDatabase.getInstance(application)!!
    private val diaryDao = database.diaryDao()

    // diary 데이터베이스의 모든 데이터 가져오기
    fun getAll(): List<Diary>{
        return diaryDao.getAll()
    }

    // 파라미터로 받은 키워드와 일기 내용을 데이터베이스에 삽입 - '오늘'의 데이터 생성에 사용
    fun insertData(keyword:String, content:String) {
        val r = Runnable {
            diaryDao.insertDiaryData(keyword, content)
        }

        val thread = Thread(r)
        thread.start()
    }

    // 파라미터로 받은 키워드와 일기 내용을 데이터베이스에 업데이트
    fun updateData(keyword: String, content: String){
        val r = Runnable {
            diaryDao.updateDiaryData(keyword, content)
        }

        val thread = Thread(r)
        thread.start()
    }

    // 오늘 일기의 키워드 불러오기
    fun getTodayKeyword(): String {
        var todayKeyword: String = "Get Today Keyword"

        val r = Runnable {
            todayKeyword = diaryDao.getTodayKeyword()
            println("\ntodayKeyword from DB in Runnable: "+todayKeyword)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in getTodayKeyword")
        }

        return todayKeyword
    }

    // 오늘 일기 데이터 존재 여부
    fun isDataExists(): Boolean {
        var isExists: Boolean = false
        println("isExists: " + isExists)

        val r = Runnable {
            isExists = diaryDao.isDataExist()
            println("isExists: " + isExists)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in isDataExists")
        }
        println("isExist: " + isExists)
        return isExists
    }

    // 오늘 일기의 내용 불러오기
    fun getDiaryContent(): String {
        var diaryContent: String = "Get Today Diary Content"

        val r = Runnable {
            diaryContent = diaryDao.getDiaryContent()
            println("\ndiaryContent from DB in Runnable: "+diaryContent)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in getDiaryContent")
        }

        return diaryContent

    }

    //RoomDB로부터 날짜별 일기 키워드 가져오기
    fun getDateKeyword(date:String): String {
        var dateKeyword: String = "Get date Keyword"

        val r = Runnable {
            dateKeyword = diaryDao.getDateKeyword(date)
            println("\ntodayKeyword from DB in Runnable: "+dateKeyword)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in getTodayKeyword")
        }

        return dateKeyword
    }

    //RoomDB로부터 날짜별 일기 존재하는지 여부 가져오기
    fun isDateDataExists(date:String): Boolean {
        var isExists: Boolean = false
        println("isExists: " + isExists)

        val r = Runnable {
            isExists = diaryDao.isDateDataExist(date)
            println("isExists: " + isExists)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in isDataExists")
        }
        println("isExist: " + isExists)
        return isExists
    }

    //RoomDB로부터 날짜별 일기 내용 가져오기
    fun getDateDiaryContent(date:String): String {
        var diaryDateContent: String = "Get Today Diary Content"

        val r = Runnable {
            diaryDateContent = diaryDao.getDateContent(date)
            println("\ndiaryContent from DB in Runnable: "+diaryDateContent)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in getDiaryContent")
        }

        return diaryDateContent

    }

}