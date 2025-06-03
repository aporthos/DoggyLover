package com.portes.doggylover.feature.dogs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.portes.doggylover.core.ui.DogItem
import com.portes.doggylover.core.ui.EmptyScreen
import com.portes.doggylover.core.ui.LoadingScreen

@Composable
fun DogsScreen(viewModel: DogsViewModel = hiltViewModel()) {
    val dogsState by viewModel.dogsState.collectAsStateWithLifecycle()
    DogsScreen(dogsState, viewModel::onTriggerEvent)
}

@Composable
fun DogsScreen(
    dogsState: DogsUiState,
    onEvents: (DogsUiEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dogs") },
            )
        },
        content = { padding ->
            PullToRefreshBox(
                modifier = Modifier.padding(padding),
                isRefreshing = (dogsState as? DogsUiState.Items)?.isRefreshing == true,
                onRefresh = {
                    onEvents(DogsUiEvents.OnRefresh)
                },
            ) {
                when (dogsState) {
                    is DogsUiState.Loading -> LoadingScreen()

                    is DogsUiState.Items -> {
                        LazyColumn {
                            items(dogsState.dogs) { item ->
                                DogItem(
                                    dog = item,
                                    onFavoriteClick = {
                                        onEvents(DogsUiEvents.OnFavorite(item))
                                    },
                                )
                            }
                        }
                    }

                    is DogsUiState.Error -> EmptyScreen()
                }
            }
        },
    )
}
