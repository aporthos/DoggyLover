package com.portes.doggylover.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.portes.doggylover.core.designsystem.R as Designsystem

@Composable
fun EmptyScreen(contentAction: @Composable () -> Unit) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(Designsystem.raw.empty))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = 1,
        )

//        Column {
        LottieAnimation(
            modifier =
                Modifier
                    .size(150.dp),
            composition = composition,
            progress = { progress },
        )
        contentAction()
//        }
    }
}
