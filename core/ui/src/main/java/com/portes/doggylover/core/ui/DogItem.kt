package com.portes.doggylover.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.portes.doggylover.core.models.domain.Dog

@Composable
fun LazyItemScope.DogItem(dog: Dog) {
    Card(
        modifier =
            Modifier
                .fillParentMaxWidth()
                .padding(8.dp),
    ) {
        Text(dog.name)
    }
}
