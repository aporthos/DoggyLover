package com.portes.doggylover.core.data

import com.portes.doggylover.core.data.datasource.DogsLocalDataSource
import com.portes.doggylover.core.data.datasource.DogsRemoteDataSource
import com.portes.doggylover.core.domain.DogsRepository
import com.portes.doggylover.core.models.domain.Dog
import com.portes.doggylover.core.models.network.DogNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DogsRepositoryImpl
    @Inject
    constructor(
        private val remoteDataSource: DogsRemoteDataSource,
        private val localDataSource: DogsLocalDataSource,
    ) : DogsRepository {
        override fun getDogs(forceRefresh: Boolean): Flow<Result<List<Dog>>> =
            object : SyncRemoteLocalManager<List<DogNetwork>, List<Dog>>() {
                // We call remote when forceRefresh is true or when local is empty
                override suspend fun shouldFetch(): Boolean = forceRefresh || localDataSource.getDogs().first().isEmpty()

                override fun fetchLocal(): Flow<List<Dog>> = localDataSource.getDogs()

                override suspend fun fetchRemote(): Result<List<DogNetwork>> = remoteDataSource.getDogs()

                override suspend fun saveResult(items: List<DogNetwork>) {
                    localDataSource.addDogs(items)
                }
            }.asFlow()

        override fun getFavoritesDogs(): Flow<List<Dog>> = localDataSource.getFavoritesDogs()

        override suspend fun setFavoriteDog(
            name: String,
            isFavorite: Int,
        ): Boolean = localDataSource.setFavoriteDog(name, isFavorite)
    }
