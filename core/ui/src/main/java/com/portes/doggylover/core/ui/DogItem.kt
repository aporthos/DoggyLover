package com.portes.doggylover.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.portes.doggylover.core.models.ui.DogUi
import com.portes.doggylover.core.designsystem.R as Designsystem

@Composable
fun LazyItemScope.DogItem(
    dog: DogUi,
    onFavoriteClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillParentMaxWidth()
                .padding(8.dp),
    ) {
        Row {
            Column {
                DogImage(
                    urlImage = dog.image,
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = dog.name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    IconButton(onClick = {
                        onFavoriteClick()
                    }) {
                        Icon(
                            painter = painterResource(id = dog.iconFavorite),
                            contentDescription = null,
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = Designsystem.drawable.cake),
                        contentDescription = null,
                    )
                    Text("${dog.age}")
                }
                Text(text = dog.description, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Composable
fun DogImage(urlImage: String) {
    AsyncImage(
        modifier =
            Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp)),
        contentScale = ContentScale.Crop,
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(urlImage)
                .build(),
        placeholder = painterResource(Designsystem.drawable.placeholder),
        error = painterResource(Designsystem.drawable.placeholder),
        contentDescription = null,
    )
}

@Preview(showBackground = true)
@Composable
fun DogList() {
    val dogs =
        listOf(
            DogUi(
                name = "Loren Cox",
                description = "He is much more passive and is the first to suggest to rescue and not eat The Little Pilot",
                age = 5,
                image = "image",
                isFavorite = false,
                iconFavorite = 12,
            ),
        )
    LazyColumn {
        items(dogs) { dog ->
            DogItem(dog, {})
        }
    }
}
