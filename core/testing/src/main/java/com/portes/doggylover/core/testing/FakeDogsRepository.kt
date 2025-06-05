package com.portes.doggylover.core.testing

import com.portes.doggylover.core.domain.DogsRepository
import com.portes.doggylover.core.models.domain.Dog
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class FakeDogsRepository : DogsRepository {
    private val dogs: MutableSharedFlow<Result<List<Dog>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getDogs(forceRefresh: Boolean): Flow<Result<List<Dog>>> = dogs

    override fun getFavoritesDogs(): Flow<List<Dog>> = dogs.map { it.getOrDefault(emptyList()) }

    override suspend fun setFavoriteDog(
        name: String,
        isFavorite: Int,
    ): Boolean = true

    fun addDogs(dog: Result<List<Dog>>) {
        dogs.tryEmit(dog)
    }
}
