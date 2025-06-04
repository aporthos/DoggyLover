package com.portes.doggylover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portes.doggylover.core.domain.DataStoreRepository
import com.portes.doggylover.navigation.MainSections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        dataStoreRepository: DataStoreRepository,
    ) : ViewModel() {
        val showOnboarding: StateFlow<MainSections> =
            dataStoreRepository.canShowOnboardingStream
                .map { canShowOnboarding ->
                    if (canShowOnboarding) {
                        MainSections.Onboarding
                    } else {
                        MainSections.Home
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = MainSections.Home,
                )
    }
