package com.example.sokdaksokdak.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [User::class, Diary::class],
    version = 2, // DB에 관련된 내용이 수정되면 버전의 업그레이드가 필요함
    exportSchema = false
)

// RoomDB - Singleton 패턴 적용
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun diaryDao() : DiaryDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "sokdak_database1"
                    )
                        .build()
                }
            }
            return instance
        }
    }
}





