package com.portes.doggylover.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.portes.doggylover.core.designsystem.theme.Gray
import com.portes.doggylover.core.models.ui.DogUi

@Composable
fun DogItem(
    dog: DogUi,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onClick()
                },
    ) {
        Card(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .height(180.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(start = 148.dp, end = 16.dp),
            ) {
                IconTitle(dog, onFavoriteClick)
                Text(
                    text = dog.description,
                    color = Gray,
                    maxLines = 3,
                    minLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(16.dp))
                DogAge(dog.age)
            }
        }
        DogImage(
            urlImage = dog.image,
        )
    }
}

@Composable
private fun IconTitle(
    dog: DogUi,
    onFavoriteClick: () -> Unit,
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
                iconFavorite = 2131165275,
            ),
        )
    LazyColumn {
        items(dogs) { dog ->
            DogItem(dog, {}, {})
        }
    }
}
