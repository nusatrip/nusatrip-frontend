package com.example.nusatrip.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nusatrip.ui.navigation.BottomNavItem
import com.example.nusatrip.ui.navigation.NavGraph
import com.example.nusatrip.ui.navigation.Routes
import com.example.nusatrip.ui.screens.home.HomeScreen
import com.example.nusatrip.ui.screens.localconnect.LocalConnectScreen
import com.example.nusatrip.ui.screens.localconnect.LocalConnectDetailScreen
import com.example.nusatrip.ui.screens.profile.ProfileScreen
import com.example.nusatrip.ui.screens.smartplanner.itinerary.ItineraryScreen
import com.example.nusatrip.ui.screens.smartplanner.planlist.PlanListScreen
import com.example.nusatrip.viewmodel.AuthViewModel

// --- Design System Constants ---
private val PrimaryBrandColor = Color(0xFF762727)
private val InactiveIconColor = Color(0xFF9E9E9E)
private val NavBarBackgroundColor = Color.White

/**
 * The main entry point for the UI layout.
 *
 * This component handles:
 * 1. The global Navigation Controller.
 * 2. Window Insets management for Edge-to-Edge display.
 * 3. Conditional rendering of the Bottom Navigation Bar.
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // Observe the current back stack to determine navigation state
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    // Whitelist: Routes that require the Bottom Navigation Bar
    val mainTabRoutes = listOf(
        Routes.HOME,
        Routes.LOCAL_CONNECT,
        Routes.SMART_PLANNER,
        Routes.PROFILE
    )

    val showBottomBar = currentRoute in mainTabRoutes

    Scaffold(
        // Set transparent to allow full-screen content (e.g., Login) to draw behind system bars
        containerColor = Color.Transparent,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if (currentRoute != null && !currentRoute.startsWith("localconnect/detail")) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.HOME) {
                HomeScreen()
            }
            composable(Routes.LOCAL_CONNECT) {
                LocalConnectScreen(
                    onNavigateToDetail = { detailId, detailType ->
                        navController.navigate(
                            Routes.localConnectDetail(detailId, detailType.name)
                        )
                    }
                )
            }

            composable(
                route = Routes.LOCAL_CONNECT_DETAIL,
                arguments = listOf(
                    navArgument("detailId") { type = NavType.StringType },
                    navArgument("detailType") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val detailId = backStackEntry.arguments?.getString("detailId") ?: ""
                val detailType = backStackEntry.arguments?.getString("detailType") ?: ""

                LocalConnectDetailScreen(
                    detailId = detailId,
                    detailType = detailType,
                    onBackClick = { navController.popBackStack() },
                    onRouteClick = {
                        // TODO: Navigate to map/route screen
                    }
                )
            }

            composable(Routes.SMART_PLANNER) {
                PlanListScreen(navController = navController)
            }

            composable(Routes.ITINERARY) {
                ItineraryScreen(navController = navController)
            }

            composable(Routes.PROFILE) {
                ProfileScreen(
                    onLogout = {
                        authViewModel.logout()
                        onLogout()
                    }
                )
            }
        }

        NavGraph(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = targetModifier
        )
    }
}

/**
 * A custom implementation of the Bottom Navigation Bar.
 *
 * Design Specification:
 * - Style: Floating Capsule.
 * - Elevation: High shadow elevation.
 * - Alignment: Vertically centered icons with no labels.
 */
@Composable
private fun FloatingCapsuleNavigationBar(
    navController: NavHostController
) {
    // Configuration of Navigation Items
    val navigationItems = listOf(
        BottomNavItem(
            route = Routes.HOME,
            label = "Home",
            iconRes = R.drawable.home_navbar
        ),
        BottomNavItem(
            route = Routes.LOCAL_CONNECT,
            label = "Local Connect",
            iconRes = R.drawable.localconnect_navbar
        ),
        BottomNavItem(
            route = Routes.SMART_PLANNER,
            label = "Smart Planner",
            iconRes = R.drawable.smartplanner_navbar
        ),
        BottomNavItem(
            route = Routes.PROFILE,
            label = "Profile",
            iconRes = R.drawable.profile_navbar
        )
    )

    // Visual Style: Fully rounded corners (50% radius)
    val capsuleShape = RoundedCornerShape(percent = 50)

    NavigationBar(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 40.dp)
            .height(64.dp)
            .shadow(elevation = 12.dp, shape = capsuleShape)
            .clip(capsuleShape),
        containerColor = NavBarBackgroundColor,
        tonalElevation = 0.dp,
        // Disable default system insets to ensure icons remain vertically centered
        windowInsets = WindowInsets(0.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        navigationItems.forEach { item ->
            // Robust hierarchy check to handle nested navigation scenarios
            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(32.dp)
                    )
                },
                // UX Decision: Labels are hidden for a cleaner aesthetic
                label = null,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBrandColor,
                    unselectedIconColor = InactiveIconColor,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}