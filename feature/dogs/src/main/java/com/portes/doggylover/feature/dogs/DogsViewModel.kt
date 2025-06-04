package com.portes.doggylover.feature.dogs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.portes.doggylover.core.domain.usecases.GetDogsUseCase
import com.portes.doggylover.core.domain.usecases.UpdateFavoriteDogUseCase
import com.portes.doggylover.core.models.ui.domainToUis
import com.portes.doggylover.core.ui.BaseViewModel
import com.portes.doggylover.core.ui.InfoDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel
    @Inject
    constructor(
        private val getDogsUseCase: GetDogsUseCase,
        private val updateFavoriteDogUseCase: UpdateFavoriteDogUseCase,
    ) : BaseViewModel<DogsUiEvents, Nothing>() {
        private val isRefreshing = MutableStateFlow(false)

        private var _infoDialogState = mutableStateOf<InfoDialogState>(InfoDialogState.Hide)
        var infoDialogState: MutableState<InfoDialogState> = _infoDialogState

        val dogsState: StateFlow<DogsUiState> =
            isRefreshing
                .combine(getDogsUseCase(GetDogsUseCase.Params(false))) { isRefreshing, result ->
                    result.fold(
                        onSuccess = { dogs ->
                            DogsUiState.Items(isRefreshing = isRefreshing, dogs = dogs.domainToUis())
                        },
                        onFailure = { DogsUiState.Error },
                    )
                }.stateIn(
                    scope = viewModelScope,
                    initialValue = DogsUiState.Loading,
                    started = SharingStarted.WhileSubscribed(),
                )

        override fun onTriggerEvent(event: DogsUiEvents) {
            when (event) {
                DogsUiEvents.OnRetry -> {}
                DogsUiEvents.OnRefresh -> onRefresh()
                is DogsUiEvents.OnFavorite -> updateFavoriteDog(event.dog.name, event.dog.isFavorite)
                is DogsUiEvents.OnShowInfoDialog -> {
                    _infoDialogState.value = InfoDialogState.Show(event.dog)
                }

                DogsUiEvents.OnHideInfoDialog -> _infoDialogState.value = InfoDialogState.Hide
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

        private fun onRefresh() {
            viewModelScope.launch {
                isRefreshing.emit(true)
                getDogsUseCase(GetDogsUseCase.Params(true)).first()
                delay(1000)
                isRefreshing.emit(false)
            }
        }
    }
