package com.portes.doggylover.core.domain

import com.portes.doggylover.core.models.domain.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    fun getDogs(forceRefresh: Boolean): Flow<Result<List<Dog>>>

    fun getFavoritesDogs(): Flow<List<Dog>>

    suspend fun setFavoriteDog(
        name: String,
        isFavorite: Int,
    ): Boolean
}
