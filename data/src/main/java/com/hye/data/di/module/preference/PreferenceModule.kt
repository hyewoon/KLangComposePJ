package com.hye.data.di.module.preference

import com.hye.data.preferences.PreferencesDataStoreManager
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    @Singleton
    abstract fun bindsPreferencesDataStore(preferencesManager: PreferencesDataStoreManager): PreferencesDataStoreRepository
}