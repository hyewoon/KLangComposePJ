package com.hye.data.di.module.impl

import com.hye.domain.repository.roomdb.StudyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class StudyRepositoryImpl {

    @Binds
    @Singleton
    abstract fun bindStudyRepository(studyRepositoryImpl: StudyRepositoryImpl): StudyRepository
}