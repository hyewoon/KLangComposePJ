package com.hye.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* dap
* */
@Database(
    entities = [TargetWord::class, TargetWordExampleInfo::class, TargetWordPronunciationInfo::class],
    version = 2,
)
abstract class TargetWordRoomDatabase : RoomDatabase() {

   abstract fun targetWordDao(): TargetWordDao

/*    companion object {
        private var INSTANCE: TargetWordRoomDatabase? = null
        fun getInstance(context: Context): TargetWordRoomDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        TargetWordRoomDatabase::class.java,
                        "target_word_database"
                    ).fallbackToDestructiveMigration(false)// 버전업그레이드 할때
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }*/
}