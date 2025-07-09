package com.hye.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TargetWordEntity::class, TargetWordEntity.TargetWordExampleInfo::class, TargetWordEntity.TargetWordPronunciationInfo::class],
    version = 1,
)
abstract class TargetWordRoomDatabase : RoomDatabase() {

    abstract fun targetWordDao(): TargetWordDao

    companion object {
        private var INSTANCE: TargetWordRoomDatabase? = null
        fun getInstance(context: Context): TargetWordRoomDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        TargetWordRoomDatabase::class.java,
                        "target_word_database"
                    ).fallbackToDestructiveMigration(false)
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}