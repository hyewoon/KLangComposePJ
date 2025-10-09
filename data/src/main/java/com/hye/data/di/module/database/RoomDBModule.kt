package com.hye.data.di.module.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hye.data.room.TargetWordRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TargetWordRoomDatabase {
        val migration4to5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE target_word ADD COLUMN isBookmarked INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE target_word ADD COLUMN bookmarkedTimeStamp INTEGER NOT NULL DEFAULT 0")
            }
        }

        return Room.databaseBuilder(
            context,
            TargetWordRoomDatabase::class.java,
            "target_word_db"
        )
            .addMigrations(migration4to5)
            .build()
    }


    @Provides
    fun provideTargetWordDao(database: TargetWordRoomDatabase) = database.targetWordDao()

}
