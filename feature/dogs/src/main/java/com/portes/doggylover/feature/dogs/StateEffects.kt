package com.portes.doggylover.feature.dogs

import com.portes.doggylover.core.models.domain.Dog
import com.portes.doggylover.core.ui.ViewEvent

sealed interface DogsUiState {
    data class Items(
        val isRefreshing: Boolean,
        val dogs: List<Dog>,
    ) : DogsUiState

    data object Error : DogsUiState

    data object Loading : DogsUiState
}

sealed interface DogsUiEvents : ViewEvent {
    data object OnRefresh : DogsUiEvents

    data object OnRetry : DogsUiEvents
}
