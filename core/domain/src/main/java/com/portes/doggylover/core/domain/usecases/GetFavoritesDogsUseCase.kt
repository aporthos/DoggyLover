package com.portes.doggylover.core.domain.usecases

import com.portes.doggylover.core.common.FlowSingleUseCase
import com.portes.doggylover.core.common.IoDispatcher
import com.portes.doggylover.core.domain.DogsRepository
import com.portes.doggylover.core.models.domain.Dog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesDogsUseCase
    @Inject
    constructor(
        private val repository: DogsRepository,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : FlowSingleUseCase<Unit, List<Dog>>(dispatcher) {
        override fun execute(params: Unit): Flow<List<Dog>> = repository.getFavoritesDogs()
    }
