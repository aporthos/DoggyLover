package com.portes.doggylover.feature.dogs

import androidx.lifecycle.viewModelScope
import com.portes.doggylover.core.domain.GetDogsUseCase
import com.portes.doggylover.core.ui.BaseViewModel
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
    ) : BaseViewModel<DogsUiEvents, Nothing>() {
        private val isRefreshing = MutableStateFlow(false)
        val dogsState: StateFlow<DogsUiState> =
            isRefreshing
                .combine(getDogsUseCase(GetDogsUseCase.Params(false))) { isRefreshing, result ->
                    result.fold(
                        onSuccess = { dogs ->
                            DogsUiState.Items(isRefreshing = isRefreshing, dogs = dogs)
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
            }
        }

        private fun onRefresh() {
            viewModelScope.launch {
                isRefreshing.emit(true)
                getDogsUseCase(GetDogsUseCase.Params(true)).first()
                delay(2000)
                isRefreshing.emit(false)
            }
        }
    }
