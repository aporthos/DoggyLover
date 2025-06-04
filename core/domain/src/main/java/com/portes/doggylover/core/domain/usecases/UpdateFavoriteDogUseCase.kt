package com.portes.doggylover.core.domain.usecases

import com.portes.doggylover.core.common.IoDispatcher
import com.portes.doggylover.core.common.UseCase
import com.portes.doggylover.core.domain.DogsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateFavoriteDogUseCase
    @Inject
    constructor(
        private val repository: DogsRepository,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : UseCase<UpdateFavoriteDogUseCase.Params, Result<Boolean>>(dispatcher) {
        data class Params(
            val name: String,
            val isFavorite: Boolean,
        )

        override suspend fun execute(params: Params): Result<Boolean> {
            val isFavorite = if (params.isFavorite) 1 else 0
            val result = repository.setFavoriteDog(params.name, isFavorite)
            return if (result) {
                Result.success(true)
            } else {
                Result.failure(Exception("Error update dog"))
            }
        }
    }
