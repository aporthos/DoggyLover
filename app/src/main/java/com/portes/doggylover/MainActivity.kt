package com.portes.doggylover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.portes.doggylover.core.designsystem.theme.DoggyLoverTheme
import com.portes.doggylover.feature.onboarding.OnboardingScreen
import com.portes.doggylover.navigation.HomeMainContainer
import com.portes.doggylover.navigation.rememberDoggyLoverAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberDoggyLoverAppState()
            DoggyLoverTheme {
                NavHost(
                    navController = appState.navController,
                    startDestination = "Home",
                ) {
                    composable("Home") {
                        HomeMainContainer(
                            items = appState.bottomBarRoutes,
                        )
                    }
                    composable("Onboarding") {
                        OnboardingScreen()
                    }
                }
            }
        }
    }
}
