package com.portes.doggylover.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.portes.doggylover.core.designsystem.R

@Composable
fun DogImage(urlImage: String) {
    AsyncImage(
        modifier =
            Modifier
                .size(130.dp, 210.dp)
                .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillHeight,
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(urlImage)
                .build(),
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.placeholder),
        contentDescription = null,
    )
}
