package com.example.nusatrip.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nusatrip.ui.screens.auth.login.LoginScreen
import com.example.nusatrip.ui.screens.auth.register.RegisterScreen
import com.example.nusatrip.ui.screens.home.HomeScreen
import com.example.nusatrip.ui.screens.localconnect.LocalConnectScreen
import com.example.nusatrip.ui.screens.onboarding.OnboardingScreen
import com.example.nusatrip.ui.screens.profile.ProfileRoute
import com.example.nusatrip.ui.screens.smartplanner.itinerary.ItineraryScreen
import com.example.nusatrip.ui.screens.smartplanner.planlist.PlanListScreen
import com.example.nusatrip.ui.screens.splash.SplashScreen
import com.example.nusatrip.viewmodel.AuthViewModel

/**
 * The central Navigation Graph for the application.
 * Defines all reachable destinations and their navigation logic.
 */
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.SPLASH
) {
    // Shared ViewModel instance scoped to the navigation graph
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // --- Authentication & Onboarding Flows ---

        composable(route = Routes.SPLASH) {
            SplashScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(route = Routes.ONBOARDING) {
            OnboardingScreen(navController = navController)
        }

        composable(route = Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    // Navigate directly to HOME (not MAIN), clearing the login screen from backstack
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                },
                authViewModel = authViewModel
            )
        }

        composable(route = Routes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    authViewModel.resetAuthState()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                authViewModel = authViewModel
            )
        }

        // --- Main App Feature Flows (Bottom Bar visible) ---

        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(Routes.LOCAL_CONNECT) {
            LocalConnectScreen()
        }

        composable(route = Routes.SMART_PLANNER) {
            PlanListScreen(navController = navController)
        }

        composable(Routes.ITINERARY) {
            ItineraryScreen(navController = navController)
        }

        composable(Routes.PROFILE) {
            // Using the Stateful Route created previously
            ProfileRoute(
                authViewModel = authViewModel,
                onLogout = {
                    authViewModel.logout()
                    // CRITICAL: Navigate to LOGIN and clear the entire backstack
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Removed: composable(Routes.MAIN)
        // Reason: Nested navigation logic has been moved to MainScreen.kt to fix bottom bar issues.
    }
}