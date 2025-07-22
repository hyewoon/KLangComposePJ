package com.hye.data.di.module

import com.hye.data.repository.FireStoreRepositoryImpl
import com.hye.data.repository.StudyRepositoryImpl
import com.hye.domain.repository.firestore.FireStoreRepository
import com.hye.domain.repository.roomdb.StudyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AllRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFireStoreRepository(
        fireStoreRepositoryImpl: FireStoreRepositoryImpl
    ): FireStoreRepository


    @Binds
    @Singleton
    abstract fun bindStudyRepository(studyRepositoryImpl: StudyRepositoryImpl): StudyRepository
}