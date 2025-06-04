package com.portes.doggylover.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.portes.doggylover.R
import com.portes.doggylover.core.designsystem.R as Designsystem

const val ROUTE_DOGS = "dogs"
const val ROUTE_FAVORITES = "favorites"

sealed class MainSections(
    val route: String,
) {
    data object Home : MainSections("Home")

    data object Onboarding : MainSections("Onboarding")
}

sealed class HomeSections(
    val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    data object Dogs :
        HomeSections(
            title = R.string.name_title_dogs,
            icon = Designsystem.drawable.dogs,
            route = ROUTE_DOGS,
        )

    data object Favorites :
        HomeSections(
            title = R.string.name_title_favorites,
            icon = Designsystem.drawable.favorite_selected,
            route = ROUTE_FAVORITES,
        )
}

@Composable
fun rememberDoggyLoverAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        DoggyLoverAppState(navController)
    }

@Stable
class DoggyLoverAppState(
    val navController: NavHostController,
) {
    val bottomBarRoutes =
        listOf(
            HomeSections.Dogs,
            HomeSections.Favorites,
        )

    fun navigateToBottomBarRoute(route: String) {
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = false
                    saveState = true
                }
            }
        }
    }
}
