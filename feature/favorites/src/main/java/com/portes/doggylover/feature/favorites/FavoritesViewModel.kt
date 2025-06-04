package com.portes.doggylover.feature.favorites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.portes.doggylover.core.domain.usecases.GetFavoritesDogsUseCase
import com.portes.doggylover.core.domain.usecases.UpdateFavoriteDogUseCase
import com.portes.doggylover.core.models.ui.domainToUis
import com.portes.doggylover.core.ui.BaseViewModel
import com.portes.doggylover.core.ui.InfoDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
    @Inject
    constructor(
        getFavoritesDogsUseCase: GetFavoritesDogsUseCase,
        private val updateFavoriteDogUseCase: UpdateFavoriteDogUseCase,
    ) : BaseViewModel<DogsUiEvents, DogsEffects>() {
        private var _infoDialogState = mutableStateOf<InfoDialogState>(InfoDialogState.Hide)
        var infoDialogState: MutableState<InfoDialogState> = _infoDialogState

        val dogsState: StateFlow<FavoritesUiState> =
            getFavoritesDogsUseCase(Unit)
                .map { favorites ->
                    if (favorites.isNotEmpty()) {
                        FavoritesUiState.Items(favorites.domainToUis())
                    } else {
                        FavoritesUiState.Error
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = FavoritesUiState.Loading,
                )

        override fun onTriggerEvent(event: DogsUiEvents) {
            when (event) {
                is DogsUiEvents.OnFavorite -> updateFavoriteDog(event.dog.name, event.dog.isFavorite)
                is DogsUiEvents.OnShowInfoDialog -> {
                    _infoDialogState.value = InfoDialogState.Show(event.dog)
                }

                DogsUiEvents.OnHideInfoDialog -> _infoDialogState.value = InfoDialogState.Hide
                DogsUiEvents.NavigateToDogs -> setEffect { DogsEffects.NavigateToDogs }
            }
        }

        private fun updateFavoriteDog(
            name: String,
            isFavorite: Boolean,
        ) {
            viewModelScope.launch {
                updateFavoriteDogUseCase(UpdateFavoriteDogUseCase.Params(name, !isFavorite))
            }
        }
    }
