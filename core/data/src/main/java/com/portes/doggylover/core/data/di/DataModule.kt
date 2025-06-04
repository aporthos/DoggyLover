package com.portes.doggylover.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.portes.doggylover.core.data.ApiServices
import com.portes.doggylover.core.data.DataStoreRepositoryImpl
import com.portes.doggylover.core.data.DogsRepositoryImpl
import com.portes.doggylover.core.data.datasource.DogsLocalDataSource
import com.portes.doggylover.core.data.datasource.DogsLocalDataSourceImpl
import com.portes.doggylover.core.data.datasource.DogsRemoteDataSource
import com.portes.doggylover.core.data.datasource.DogsRemoteDataSourceImpl
import com.portes.doggylover.core.domain.DataStoreRepository
import com.portes.doggylover.core.domain.DogsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    companion object {
        const val DOGGY_DATASTORE = "doggyDataStore"

        @Singleton
        @Provides
        fun providesService(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

        @Named(DOGGY_DATASTORE)
        @Singleton
        @Provides
        fun providesDoggyDataStore(
            @ApplicationContext context: Context,
        ): DataStore<Preferences> =
            PreferenceDataStoreFactory.create {
                context.preferencesDataStoreFile(DOGGY_DATASTORE)
            }
    }

    @Binds
    fun bindsDogsRepository(repository: DogsRepositoryImpl): DogsRepository

    @Binds
    fun bindsDogsLocalDataSource(dataSource: DogsLocalDataSourceImpl): DogsLocalDataSource

    @Binds
    fun bindsDogsRemoteDataSource(dataSource: DogsRemoteDataSourceImpl): DogsRemoteDataSource

    @Binds
    fun bindsDoggyDataStoreManager(manager: DataStoreRepositoryImpl): DataStoreRepository
}
