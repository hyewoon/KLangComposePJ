package com.hye.data.di.module.repo

import com.hye.data.repository.StudyRepositoryImpl
import com.hye.domain.repository.roomdb.StudyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class StudyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStudyRepository(studyRepositoryImpl: StudyRepositoryImpl): StudyRepository
}