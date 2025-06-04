package com.portes.doggylover.feature.favorites

import com.portes.doggylover.core.models.ui.DogUi
import com.portes.doggylover.core.ui.ViewEffect
import com.portes.doggylover.core.ui.ViewEvent

sealed interface FavoritesUiState {
    data class Items(
        val dogs: List<DogUi>,
    ) : FavoritesUiState

    data object Error : FavoritesUiState

    data object Loading : FavoritesUiState
}

sealed interface DogsUiEvents : ViewEvent {
    data class OnFavorite(
        val dog: DogUi,
    ) : DogsUiEvents

    data class OnShowInfoDialog(
        val dog: DogUi,
    ) : DogsUiEvents

    data object OnHideInfoDialog : DogsUiEvents

    data object NavigateToDogs : DogsUiEvents
}

sealed interface DogsEffects : ViewEffect {
    data object NavigateToDogs : DogsEffects
}
