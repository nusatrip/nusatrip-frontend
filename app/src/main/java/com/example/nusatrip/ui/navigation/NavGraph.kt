package com.example.nusatrip.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nusatrip.ui.MainBottomNavScreen
import com.example.nusatrip.ui.screens.auth.LoginScreen
import com.example.nusatrip.ui.screens.auth.RegisterScreen
import com.example.nusatrip.ui.screens.onboarding.OnboardingScreen
import com.example.nusatrip.ui.screens.splash.SplashScreen
import com.example.nusatrip.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.SPLASH
) {
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
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
                    navController.navigate(Routes.MAIN) {
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

        composable(route = Routes.MAIN) {
            MainBottomNavScreen(
                authViewModel = authViewModel,
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}