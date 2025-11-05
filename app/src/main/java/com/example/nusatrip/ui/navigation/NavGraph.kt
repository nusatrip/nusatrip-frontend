package com.example.nusatrip_papb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nusatrip_papb.ui.MainBottomNavScreen
import com.example.nusatrip_papb.ui.screens.auth.LoginScreen
import com.example.nusatrip_papb.ui.screens.auth.RegisterScreen
import com.example.nusatrip_papb.ui.screens.onboarding.OnboardingScreen
import com.example.nusatrip_papb.ui.screens.splash.SplashScreen

/**
 * Root Navigation Graph
 * Handles main application navigation (Splash, Onboarding, Login, Register, Main)
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.SPLASH,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Splash screen
        composable(route = Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        // Onboarding flow
        composable(route = Routes.ONBOARDING) {
            OnboardingScreen(navController = navController)
        }

        // Authentication flow
        composable(route = Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(route = Routes.REGISTER) {
            RegisterScreen(navController = navController)
        }

        // Main application flow (with bottom navigation)
        composable(route = Routes.MAIN) {
            MainBottomNavScreen()
        }
    }
}