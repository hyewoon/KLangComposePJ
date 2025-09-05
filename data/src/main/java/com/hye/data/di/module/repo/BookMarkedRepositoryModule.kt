package com.hye.data.di.module.repo

import com.hye.data.repository.BookMarkedWordsRepositoryImpl
import com.hye.domain.repository.roomdb.BookMarkedWordsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BookMarkedRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookMarkedRepository(bookMarkedRepositoryImpl: BookMarkedWordsRepositoryImpl): BookMarkedWordsRepository

}