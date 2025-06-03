package com.portes.doggylover.core.data.di

import com.portes.doggylover.core.data.ApiServices
import com.portes.doggylover.core.data.DogsRepositoryImpl
import com.portes.doggylover.core.data.datasource.DogsLocalDataSource
import com.portes.doggylover.core.data.datasource.DogsLocalDataSourceImpl
import com.portes.doggylover.core.data.datasource.DogsRemoteDataSource
import com.portes.doggylover.core.data.datasource.DogsRemoteDataSourceImpl
import com.portes.doggylover.core.domain.DogsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    companion object {
        @Singleton
        @Provides
        fun providesService(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }

    @Binds
    fun bindsDogsRepository(repository: DogsRepositoryImpl): DogsRepository

    @Binds
    fun bindsDogsLocalDataSource(dataSource: DogsLocalDataSourceImpl): DogsLocalDataSource

    @Binds
    fun bindsDogsRemoteDataSource(dataSource: DogsRemoteDataSourceImpl): DogsRemoteDataSource
}
