package com.portes.doggylover.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.portes.doggylover.core.models.ui.DogUi

sealed interface InfoDialogState {
    data class Show(
        val dog: DogUi,
    ) : InfoDialogState

    data object Hide : InfoDialogState
}

@Composable
fun InfoDialog(
    dog: DogUi,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = dog.name,
            )
        },
        text = {
            ContentInfo(dog)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                },
            ) {
                Text(text = stringResource(R.string.ok))
            }
        },
    )
}

@Composable
private fun ContentInfo(dog: DogUi) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DogImage(dog.image)
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = dog.description,
        )
        Spacer(Modifier.height(16.dp))
        DogAge(dog.age)
    }
}
