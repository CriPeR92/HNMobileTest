package com.example.hnmobiletest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        const val dbName = "news_database"

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            val tempInstace = INSTANCE
            if (tempInstace != null) return tempInstace
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, dbName).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}