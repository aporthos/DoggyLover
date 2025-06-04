package com.portes.doggylover.feature.onboarding

import com.portes.doggylover.core.models.ui.ScreenItem

sealed interface OnboardingUiState {
    data object Loading : OnboardingUiState

    data class Items(
        val items: List<ScreenItem>,
    ) : OnboardingUiState
}
