package com.hye.klangcomposepj.di

import com.hye.klangcomposepj.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @Named("apikey")
    fun provideApiKey(): String{
        return BuildConfig.API_KEY
    }
}