package com.example.nusatrip_papb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nusatrip_papb.ui.navigation.BottomNavItem
import com.example.nusatrip_papb.ui.navigation.NavGraph
import com.example.nusatrip_papb.ui.navigation.Routes
import com.example.nusatrip_papb.ui.screens.home.HomeScreen
import com.example.nusatrip_papb.ui.screens.localconnect.LocalConnectScreen
import com.example.nusatrip_papb.ui.screens.profile.ProfileScreen
import com.example.nusatrip_papb.ui.screens.smartplanner.SmartPlannerScreen

/**
 * Main screen sebagai root navigation
 * Menangani navigasi utama: Login -> Register -> Main App
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavGraph(
            navController = navController,
            startDestination = Routes.LOGIN
        )
    }
}

/**
 * Screen dengan Bottom Navigation untuk main app flow
 * Ini adalah screen yang ditampilkan setelah user login
 */
@Composable
fun MainBottomNavScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
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
                LocalConnectScreen()
            }
            composable(Routes.SMART_PLANNER) {
                SmartPlannerScreen()
            }
            composable(Routes.PROFILE) {
                ProfileScreen()
            }
        }
    }
}

/**
 * Bottom Navigation Bar Component
 */
@Composable
private fun BottomNavigationBar(
    navController: NavHostController
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            route = Routes.HOME,
            label = "Home",
            icon = Icons.Outlined.Home
        ),
        BottomNavItem(
            route = Routes.LOCAL_CONNECT,
            label = "Local Connect",
            icon = Icons.Outlined.Place
        ),
        BottomNavItem(
            route = Routes.SMART_PLANNER,
            label = "Smart Planner",
            icon = Icons.Outlined.CalendarMonth
        ),
        BottomNavItem(
            route = Routes.PROFILE,
            label = "Profile",
            icon = Icons.Outlined.Person
        )
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination
                        popUpTo(Routes.HOME) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}