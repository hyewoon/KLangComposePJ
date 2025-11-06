package com.hye.data.di.module.repo

import com.hye.data.repository.WordRepositoryImpl
import com.hye.domain.repository.api.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WordRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWordRepository(wordRepositoryImpl: WordRepositoryImpl) : WordRepository
}