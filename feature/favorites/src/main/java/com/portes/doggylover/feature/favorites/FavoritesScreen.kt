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
import com.portes.doggylover.core.ui.InfoDialog
import com.portes.doggylover.core.ui.InfoDialogState
import com.portes.doggylover.core.ui.LoadingScreen

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val dogsState by viewModel.dogsState.collectAsStateWithLifecycle()
    val infoDialogState by viewModel.infoDialogState

    FavoritesScreen(dogsState, viewModel::onTriggerEvent)

    if (infoDialogState is InfoDialogState.Show) {
        InfoDialog(
            dog = (infoDialogState as InfoDialogState.Show).dog,
            onConfirm = {
                viewModel.onTriggerEvent(DogsUiEvents.OnHideInfoDialog)
            },
            onDismiss = {
                viewModel.onTriggerEvent(DogsUiEvents.OnHideInfoDialog)
            },
        )
    }
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
                                onClick = {
                                    onEvents(DogsUiEvents.OnShowInfoDialog(item))
                                },
                            )
                        }
                    }
            }
        },
    )
}
