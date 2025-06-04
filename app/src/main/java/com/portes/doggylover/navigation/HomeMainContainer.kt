package com.portes.doggylover.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.portes.doggylover.feature.dogs.DogsScreen
import com.portes.doggylover.feature.favorites.FavoritesScreen

@Composable
fun HomeMainContainer(items: List<HomeSections>) {
    val appState = rememberDoggyLoverAppState()
    val backStackEntry by appState.navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            MainBottomAppBar(
                navBackStackEntry = backStackEntry,
                items = items,
                navigateToRoute = appState::navigateToBottomBarRoute,
            )
        },
        content = { paddingValues ->
            NavHost(
                modifier =
                    Modifier
                        .padding(paddingValues)
                        .consumeWindowInsets(paddingValues),
                navController = appState.navController,
                startDestination = HomeSections.Dogs.route,
            ) {
                composable(HomeSections.Dogs.route) {
                    DogsScreen()
                }
                composable(HomeSections.Favorites.route) {
                    FavoritesScreen(navigateToDogs = {
                        appState.navigateToBottomBarRoute(HomeSections.Dogs.route)
                    })
                }
            }
        },
    )
}
