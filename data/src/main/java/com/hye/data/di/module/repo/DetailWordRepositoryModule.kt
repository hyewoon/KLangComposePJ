package com.hye.data.di.module.repo

import com.hye.data.repository.DetailWordRepositoryImpl
import com.hye.domain.repository.api.DetailWordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailWordRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDetailWordRepository(detailWordRepositoryImpl: DetailWordRepositoryImpl) : DetailWordRepository
}