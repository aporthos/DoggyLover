package com.portes.doggylover.feature.onboarding

import com.portes.doggylover.core.ui.ViewEvent

sealed interface OnboardingUiEvents : ViewEvent {
    data object OnStart : OnboardingUiEvents

    data object OnSkip : OnboardingUiEvents
}
