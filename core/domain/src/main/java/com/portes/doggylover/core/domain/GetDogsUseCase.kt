package com.portes.doggylover.core.domain

import com.portes.doggylover.core.common.FlowSingleUseCase
import com.portes.doggylover.core.common.IoDispatcher
import com.portes.doggylover.core.models.domain.Dog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDogsUseCase
    @Inject
    constructor(
        private val repository: DogsRepository,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : FlowSingleUseCase<GetDogsUseCase.Params, Result<List<Dog>>>(dispatcher) {
        data class Params(
            val forceRefresh: Boolean = false,
        )

        override fun execute(params: Params): Flow<Result<List<Dog>>> = repository.getDogs(params.forceRefresh)
    }
