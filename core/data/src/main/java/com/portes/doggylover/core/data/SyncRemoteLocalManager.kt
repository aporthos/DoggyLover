package com.portes.doggylover.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

abstract class SyncRemoteLocalManager<Entity, Domain> {
    fun asFlow(): Flow<Result<Domain>> =
        flow {
            if (shouldFetch()) {
                fetchRemote()
                    .onSuccess { result ->
                        Timber.i("asFlow -> remote $result")
                        saveResult(result)
                    }.onFailure { throwable ->
                        Timber.i("asFlow -> onFailure $throwable")
                        emit(Result.failure(throwable))
                    }
            }
            emitAll(fetchLocal().map { Result.success(it) })
        }

    protected abstract suspend fun shouldFetch(): Boolean

    protected abstract fun fetchLocal(): Flow<Domain>

    protected abstract suspend fun fetchRemote(): Result<Entity>

    protected abstract suspend fun saveResult(items: Entity)
}
