package com.example.sokdaksokdak.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.PipedWriter

// 사용자 정보가 저장되는 테이블
// 사용자 이름, 사용자 생일
@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "user_name")
    var userName: String?,
    @ColumnInfo(name = "birth")
    var birth: Int
){
    @PrimaryKey(autoGenerate = true) var userId: Int = 0
}