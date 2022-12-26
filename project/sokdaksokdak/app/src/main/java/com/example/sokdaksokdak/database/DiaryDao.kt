package com.example.sokdaksokdak.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface DiaryDao {
    //일기의 내용과 관련한 DB
    @Query("SELECT * FROM diary_table")
    fun getAll(): List<Diary>

    @Insert
    fun insertDiary(diary: Diary)

    @Query("Select * From diary_table")
    fun getDiary() : Diary

    @Delete
    fun delete(diary: Diary)

    @Query("SELECT date FROM diary_table")
    fun getDate(): List<Int>

    //날짜별 키워드 가져오기
    @Query("select keyword from diary_table where date=:date")
    fun getDateKeyword(date:String): String
    //날짜별 데이터 존재하는지 가져오기
    @Query("select exists (select keyword from diary_table where date=:date)")
    fun isDateDataExist(date:String): Boolean
    //날짜별 일기 가져오기
    @Query("select diary_context from diary_table where date=:date")
    //@Query("select * from diary_table where date=:date")
    fun getDateContent(date:String):String

    @Query("delete from diary_table")
    fun deleteData()

    // 새로운 일기 데이터 생성
    @Query("INSERT INTO diary_table(keyword, date, diary_context) VALUES (:keyword, (select date('now', 'localtime')), :diary_context)")
    fun insertDiaryData(keyword: String, diary_context: String)

    // 일기 키워드, 내용 업데이트
    @Query("UPDATE diary_table SET keyword=:keyword, diary_context=:diary_context WHERE date=(select date('now', 'localtime'))")
    fun updateDiaryData(keyword: String, diary_context: String)

    // 날짜로 keyword 가져오기
    @Query("select keyword from diary_table where date=(select date('now', 'localtime'))")
    fun getTodayKeyword(): String

    // 오늘의 일기 데이터 존재 여부
    @Query("select exists (select keyword from diary_table where date=(select date('now', 'localtime')))")
    fun isDataExist(): Boolean

    // 오늘의 일기 내용 불러오기
    @Query("select diary_context from diary_table where date=(select date('now', 'localtime'))")
    fun getDiaryContent(): String


}