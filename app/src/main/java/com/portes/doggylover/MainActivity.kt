package com.portes.doggylover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.portes.doggylover.core.designsystem.theme.DoggyLoverTheme
import com.portes.doggylover.feature.onboarding.OnboardingScreen
import com.portes.doggylover.navigation.HomeMainContainer
import com.portes.doggylover.navigation.MainSections
import com.portes.doggylover.navigation.rememberDoggyLoverAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberDoggyLoverAppState()
            DoggyLoverTheme {
                val startDestination by viewModel.showOnboarding.collectAsStateWithLifecycle()
                NavHost(
                    navController = appState.navController,
                    startDestination = startDestination.route,
                ) {
                    composable(MainSections.Home.route) {
                        HomeMainContainer(
                            items = appState.bottomBarRoutes,
                        )
                    }
                    composable(MainSections.Onboarding.route) {
                        OnboardingScreen()
                    }
                }
            }
        }
    }
}
