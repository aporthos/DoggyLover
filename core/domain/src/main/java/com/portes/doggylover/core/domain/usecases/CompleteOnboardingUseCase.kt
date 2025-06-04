package com.portes.doggylover.core.domain.usecases

import com.portes.doggylover.core.common.IoDispatcher
import com.portes.doggylover.core.common.UseCase
import com.portes.doggylover.core.domain.DataStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CompleteOnboardingUseCase
    @Inject
    constructor(
        private val repository: DataStoreRepository,
        @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : UseCase<Unit, Unit>(dispatcher) {
        override suspend fun execute(params: Unit) = repository.completeOnboarding()
    }
