package com.example.sokdaksokdak.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    //본 어플의 유저들에 관한 DB
    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Query("Select * From user_table")
    fun getLogin() : User

    @Delete
    fun delete(user: User)

    // 사용자 이름으로 생일 검색
    @Query("SELECT birth FROM user_table WHERE user_name=:userName")
    fun getBirthByName(userName: String): Int

    // 사용자 이름 가져오기
    @Query("select user_name from user_table")
    fun getNameList():List<String>

    // 사용자 이름에 해당하는 모든 정보 가져오기
    @Query("select * from user_table where user_name=:name")
    fun getName(name: String):User
}