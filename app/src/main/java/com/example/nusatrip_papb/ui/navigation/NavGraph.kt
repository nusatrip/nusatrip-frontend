package com.example.nusatrip_papb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nusatrip_papb.ui.MainBottomNavScreen
import com.example.nusatrip_papb.ui.screens.auth.LoginScreen
import com.example.nusatrip_papb.ui.screens.auth.RegisterScreen

/**
 * Root Navigation Graph
 * Menangani navigasi utama aplikasi (Login, Register, Main)
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.LOGIN,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Auth Flow
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController = navController)
        }

        // Main App Flow (dengan bottom navigation)
        composable(Routes.MAIN) {
            MainBottomNavScreen()
        }
    }
}