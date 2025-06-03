package com.portes.doggylover.feature.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.portes.doggylover.core.ui.DogItem
import com.portes.doggylover.core.ui.EmptyScreen
import com.portes.doggylover.core.ui.LoadingScreen

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val dogsState by viewModel.dogsState.collectAsStateWithLifecycle()
    FavoritesScreen(dogsState, viewModel::onTriggerEvent)
}

@Composable
fun FavoritesScreen(
    favoritesUiState: FavoritesUiState,
    onEvents: (DogsUiEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
            )
        },
        content = { padding ->
            when (favoritesUiState) {
                is FavoritesUiState.Error -> EmptyScreen()
                FavoritesUiState.Loading -> LoadingScreen()
                is FavoritesUiState.Items ->
                    LazyColumn(
                        modifier = Modifier.padding(padding),
                    ) {
                        items(favoritesUiState.dogs) { item ->
                            DogItem(
                                dog = item,
                                onFavoriteClick = {
                                    onEvents(DogsUiEvents.OnFavorite(item))
                                },
                            )
                        }
                    }
            }
        },
    )
}
