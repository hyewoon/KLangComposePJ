package com.hye.data.di.module.impl

import com.hye.data.repository.FireStoreRepositoryImpl
import com.hye.domain.repository.firestore.FireStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FireStoreRepositoryImpl {

    @Singleton
    @Binds
    abstract fun bindFireStoreRepository(
        fireStoreRepositoryImpl: FireStoreRepositoryImpl
    ): FireStoreRepository
}