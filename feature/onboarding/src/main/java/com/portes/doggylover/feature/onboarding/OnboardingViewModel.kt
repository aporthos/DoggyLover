package com.portes.doggylover.feature.onboarding

import androidx.lifecycle.viewModelScope
import com.portes.doggylover.core.domain.usecases.CompleteOnboardingUseCase
import com.portes.doggylover.core.models.ui.ScreenItem
import com.portes.doggylover.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.portes.doggylover.core.designsystem.R as Designsystem

@HiltViewModel
class OnboardingViewModel
    @Inject
    constructor(
        private val completeOnboardingUseCase: CompleteOnboardingUseCase,
        private val resources: OnboardingResourcesManager,
    ) : BaseViewModel<OnboardingUiEvents, Nothing>() {
        val onboardingState: StateFlow<OnboardingUiState> =
            flow {
                emit(
                    OnboardingUiState.Items(
                        listOf(
                            ScreenItem(
                                title = resources.titleDogs,
                                message = resources.messageDogs,
                                image = Designsystem.raw.dogs,
                            ),
                            ScreenItem(
                                title = resources.titleFavorites,
                                message = resources.messageFavorites,
                                image = Designsystem.raw.favorites,
                            ),
                        ),
                    ),
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = OnboardingUiState.Loading,
            )

        override fun onTriggerEvent(event: OnboardingUiEvents) {
            when (event) {
                OnboardingUiEvents.OnStart -> onStart()
                OnboardingUiEvents.OnSkip -> onSkip()
            }
        }

        private fun onStart() =
            viewModelScope.launch {
                completeOnboardingUseCase(Unit)
            }

        private fun onSkip() =
            viewModelScope.launch {
                completeOnboardingUseCase(Unit)
            }
    }
