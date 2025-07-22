package com.hye.data.di.module.firebase


import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FireBaseModule {

    @Singleton
    @Provides
    fun provideFireStore() = FirebaseFirestore.getInstance()
}