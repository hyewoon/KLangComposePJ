package com.hye.klangcomposepj.di

import com.hye.domain.repository.firestore.FireStoreRepository
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.usecase.LoadStudyWordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLoadStudyWordUseCase(studyRepository: StudyRepository, firestoreRepository: FireStoreRepository): LoadStudyWordUseCase {
        return LoadStudyWordUseCase(studyRepository, firestoreRepository)

    }

}