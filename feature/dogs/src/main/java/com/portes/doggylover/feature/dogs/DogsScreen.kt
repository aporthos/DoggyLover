package com.portes.doggylover.feature.dogs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.portes.doggylover.core.ui.DogItem
import com.portes.doggylover.core.ui.EmptyScreen
import com.portes.doggylover.core.ui.InfoDialog
import com.portes.doggylover.core.ui.InfoDialogState
import com.portes.doggylover.core.ui.LoadingScreen
import timber.log.Timber

@Composable
fun DogsScreen(viewModel: DogsViewModel = hiltViewModel()) {
    val dogsState by viewModel.dogsState.collectAsStateWithLifecycle()
    val infoDialogState by viewModel.infoDialogState

    DogsScreen(dogsState, viewModel::onTriggerEvent)

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
fun DogsScreen(
    dogsState: DogsUiState,
    onEvents: (DogsUiEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.title_dogs)) },
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
                Timber.i("dogsState $dogsState")
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
                                    onClick = {
                                        onEvents(DogsUiEvents.OnShowInfoDialog(item))
                                    },
                                )
                            }
                        }
                    }

                    is DogsUiState.Empty ->
                        EmptyScreen(contentAction = {
                            TextButton(onClick = {
                                onEvents(DogsUiEvents.OnRetry)
                            }) {
                                Text(text = stringResource(R.string.message_retry))
                            }
                        })
                }
            }
        },
    )
}
