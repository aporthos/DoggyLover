package com.portes.doggylover.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.portes.doggylover.core.models.ui.ScreenItem
import com.portes.doggylover.feature.onboarding.components.BottomSection
import com.portes.doggylover.feature.onboarding.components.OnboardingItem

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = hiltViewModel()) {
    val onboardingState by viewModel.onboardingState.collectAsStateWithLifecycle()

    OnboardingScreen(onboardingState, viewModel::onTriggerEvent)
}

@Composable
fun OnboardingScreen(
    onboardingState: OnboardingUiState,
    onEvents: (OnboardingUiEvents) -> Unit,
) {
    when (onboardingState) {
        OnboardingUiState.Loading -> Unit
        is OnboardingUiState.Items ->
            OnboardingContent(
                items = onboardingState.items,
                onEvents = onEvents,
            )
    }
}

@Composable
fun OnboardingContent(
    items: List<ScreenItem>,
    onEvents: (OnboardingUiEvents) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        val pagerState = rememberPagerState { items.size }
        Column(modifier = Modifier.padding(padding)) {
            TextButton(
                modifier =
                    Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp),
                onClick = {
                    onEvents(OnboardingUiEvents.OnSkip)
                },
            ) {
                Text(text = stringResource(R.string.button_skip))
            }
            HorizontalPager(modifier = Modifier.weight(1f), state = pagerState) { index ->
                OnboardingItem(screen = items[index])
            }

            BottomSection(
                size = items.size,
                pagerState = pagerState,
                onEvents = onEvents,
            )
        }
    }
}
