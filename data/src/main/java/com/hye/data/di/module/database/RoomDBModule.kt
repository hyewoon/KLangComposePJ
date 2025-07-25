package com.hye.data.di.module.database

import android.content.Context

import com.hye.data.room.TargetWordRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDBModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context)= TargetWordRoomDatabase.getInstance(context)


    @Provides
    fun provideDao(database: TargetWordRoomDatabase) = database.targetWordDao()
}
