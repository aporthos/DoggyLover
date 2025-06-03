package com.portes.doggylover.core.data.datasource

import com.portes.doggylover.core.common.IoDispatcher
import com.portes.doggylover.core.data.ApiServices
import com.portes.doggylover.core.models.network.DogNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DogsRemoteDataSourceImpl
    @Inject
    constructor(
        private val apiServices: ApiServices,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : DogsRemoteDataSource {
        override suspend fun getDogs(): Result<List<DogNetwork>> =
            withContext(dispatcher) {
                try {
                    val response = apiServices.getDogs()
                    Result.success(response)
                } catch (e: Exception) {
                    Timber.e("getEvents -> $e")
                    Result.failure(e)
                }
            }
    }

interface DogsRemoteDataSource {
    suspend fun getDogs(): Result<List<DogNetwork>>
}
