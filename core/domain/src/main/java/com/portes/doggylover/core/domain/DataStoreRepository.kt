package com.portes.doggylover.core.domain

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun completeOnboarding()

    val canShowOnboardingStream: Flow<Boolean>
}
