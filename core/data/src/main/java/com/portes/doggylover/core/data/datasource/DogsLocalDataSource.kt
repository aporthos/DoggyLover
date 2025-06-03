package com.portes.doggylover.core.data.datasource

import com.portes.doggylover.core.common.IoDispatcher
import com.portes.doggylover.core.data.mappers.entityToDomains
import com.portes.doggylover.core.data.mappers.networkToEntities
import com.portes.doggylover.core.database.DogsDao
import com.portes.doggylover.core.models.domain.Dog
import com.portes.doggylover.core.models.entity.DogEntity
import com.portes.doggylover.core.models.network.DogNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogsLocalDataSourceImpl
    @Inject
    constructor(
        private val dogsDao: DogsDao,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : DogsLocalDataSource {
        override fun getDogs(): Flow<List<Dog>> =
            dogsDao
                .getDogs()
                .map(List<DogEntity>::entityToDomains)
                .flowOn(dispatcher)

        override suspend fun addDogs(dogs: List<DogNetwork>) {
            withContext(dispatcher) {
                dogsDao.insertOrIgnore(dogs.networkToEntities())
            }
        }
    }

interface DogsLocalDataSource {
    fun getDogs(): Flow<List<Dog>>

    suspend fun addDogs(dogs: List<DogNetwork>)
}
