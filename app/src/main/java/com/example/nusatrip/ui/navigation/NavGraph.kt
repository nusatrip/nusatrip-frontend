package com.example.nusatrip.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nusatrip.ui.screens.auth.login.LoginScreen
import com.example.nusatrip.ui.screens.auth.register.RegisterScreen
import com.example.nusatrip.ui.screens.explore.ExploreDetailScreen
import com.example.nusatrip.ui.screens.explore.ExploreScreen
import com.example.nusatrip.ui.screens.explore.BookingSuccessScreen
import com.example.nusatrip.ui.screens.home.HomeScreen
import com.example.nusatrip.ui.screens.localconnect.LocalConnectDetailScreen
import com.example.nusatrip.ui.screens.localconnect.LocalConnectScreen
import com.example.nusatrip.ui.screens.onboarding.OnboardingScreen
import com.example.nusatrip.ui.screens.profile.ProfileRoute
import com.example.nusatrip.ui.screens.profile.ProfileScreen
import com.example.nusatrip.ui.screens.smartplanner.generateplan.GeneratePlanScreen
import com.example.nusatrip.ui.screens.smartplanner.itinerary.ItineraryScreen
import com.example.nusatrip.ui.screens.smartplanner.planlist.PlanListScreen
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


        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(Routes.LOCAL_CONNECT) {
            LocalConnectScreen()
        }

        composable(route = Routes.SMART_PLANNER) {
            PlanListScreen(navController = navController)
        }

        composable(Routes.GENERATE_PLAN) {
            GeneratePlanScreen(navController = navController)
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
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.EXPLORE_PAGE) {
            ExploreScreen(navController = navController)
        }

        // Route Detail Screen (menerima placeId)
        composable(
            route = "explore_detail/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getInt("placeId") ?: 0
            ExploreDetailScreen(navController = navController, placeId = placeId)
        }

        // Route Booking Success (menerima placeId juga agar nama tiket sesuai)
        composable(
            route = "booking_success/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getInt("placeId") ?: 0
            BookingSuccessScreen(navController = navController, placeId = placeId)
        }

        // Local Connect Screen
        composable(Routes.LOCAL_CONNECT) {
            LocalConnectScreen(
                onNavigateToDetail = { detailId, detailType ->
                    navController.navigate(
                        Routes.localConnectDetail(detailId, detailType. name)
                    )
                }
            )
        }

        // Local Connect Detail Screen
        composable(
            route = Routes.LOCAL_CONNECT_DETAIL,
            arguments = listOf(
                navArgument("detailId") { type = NavType. StringType },
                navArgument("detailType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val detailId = backStackEntry.arguments?.getString("detailId") ?: ""
            val detailType = backStackEntry.arguments?.getString("detailType") ?: ""

            LocalConnectDetailScreen(
                detailId = detailId,
                detailType = detailType,
                onBackClick = {
                    navController.navigateUp()
                },
                onRouteClick = {
                    // TODO: Implementasi navigasi ke Google Maps atau fitur route
                    // Contoh: buka Google Maps dengan koordinat tertentu
                }
            )
        }

        // Removed: composable(Routes.MAIN)
        // Reason: Nested navigation logic has been moved to MainScreen.kt to fix bottom bar issues.
    }
}