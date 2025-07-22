package com.hye.data.di.module.mapper

import com.hye.data.datasource.firestore.mapper.DomainToRoomMapper
import com.hye.data.datasource.firestore.mapper.DtoToDomainMapper
import com.hye.data.datasource.firestore.mapper.RoomToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideDtoToDomainMapper() = DtoToDomainMapper()

    @Provides
    @Singleton
    fun provideDomainToRoomMapper() = DomainToRoomMapper()

    @Provides
    @Singleton
    fun provideRoomToDomainMapper() = RoomToDomainMapper()
}