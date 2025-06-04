package com.portes.doggylover.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun DogAge(age: Int) {
    Text(
        text = stringResource(R.string.years_old, age),
        color = Color.Black,
        style = MaterialTheme.typography.labelLarge,
    )
}
