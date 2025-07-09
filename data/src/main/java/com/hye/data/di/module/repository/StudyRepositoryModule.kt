package com.hye.data.di.module.repository

import com.hye.data.repository.StudyRepositoryImpl
import com.hye.domain.repository.roomdb.StudyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class StudyRepositoryModule {

    @Binds
    abstract fun bindStudyRepository(
        studyRepositoryImpl: StudyRepositoryImpl
    ): StudyRepository
}