package com.hye.data.di.module.repo

import com.hye.data.repository.MLKitRepositoryImpl
import com.hye.domain.repository.mlkit.MLKitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MLKitRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMLKitRepository(mlKitRepositoryImpl: MLKitRepositoryImpl): MLKitRepository
}