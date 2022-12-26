package com.example.sokdaksokdak.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.Date

// 일기 내용이 저장되는 테이블
// 키워드, 내용, 날짜
@Entity(tableName = "diary_table")
data class Diary(
    @ColumnInfo(name = "keyword")
    var keyword :String,
    @ColumnInfo(name = "date")
    var date: Int,
    @ColumnInfo(name = "diary_context")
    var diaryContext: String
){
    @PrimaryKey(autoGenerate = true) var diaryId: Int = 0
}
