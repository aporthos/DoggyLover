package com.portes.doggylover.feature.dogs

import com.portes.doggylover.core.models.ui.DogUi
import com.portes.doggylover.core.ui.ViewEvent

sealed interface DogsUiState {
    data class Items(
        val isRefreshing: Boolean,
        val dogs: List<DogUi>,
    ) : DogsUiState

    data object Empty : DogsUiState

    data object Loading : DogsUiState
}

sealed interface DogsUiEvents : ViewEvent {
    data object OnRefresh : DogsUiEvents

    data object OnRetry : DogsUiEvents

    data class OnShowInfoDialog(
        val dog: DogUi,
    ) : DogsUiEvents

    data object OnHideInfoDialog : DogsUiEvents

    data class OnFavorite(
        val dog: DogUi,
    ) : DogsUiEvents
}
