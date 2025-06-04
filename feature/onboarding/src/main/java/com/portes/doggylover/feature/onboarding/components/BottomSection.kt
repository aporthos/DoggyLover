package com.portes.doggylover.feature.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.portes.doggylover.feature.onboarding.OnboardingUiEvents
import com.portes.doggylover.feature.onboarding.R
import kotlinx.coroutines.launch

@Composable
fun BottomSection(
    size: Int,
    pagerState: PagerState,
    onEvents: (OnboardingUiEvents) -> Unit,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Indicators(size = size, index = pagerState.currentPage)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
            onClick = {
                scope.launch {
                    if (pagerState.currentPage < size - 1) {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    } else {
                        onEvents(OnboardingUiEvents.OnStart)
                    }
                }
            },
        ) {
            val text =
                if (pagerState.currentPage < size - 1) R.string.button_next else R.string.button_start
            Text(text = stringResource(text))
        }
    }
}
