package com.example.nusatrip.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nusatrip.ui.navigation.BottomNavItem
import com.example.nusatrip.ui.navigation.NavGraph
import com.example.nusatrip.ui.navigation.Routes
import com.example.nusatrip.ui.screens.HomeScreen
import com.example.nusatrip.ui.screens.localconnect.LocalConnectScreen
import com.example.nusatrip.ui.screens.profile.ProfileScreen
import com.example.nusatrip.ui.screens.smartplanner.SmartPlannerScreen
import com.example.nusatrip.viewmodel.AuthViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavGraph(
            navController = navController,
            startDestination = Routes.SPLASH
        )
    }
}

@Composable
fun MainBottomNavScreen(
    authViewModel: AuthViewModel,
    onLogout: () -> Unit
) {
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
                ProfileScreen(
                    onLogout = {
                        authViewModel.logout()
                        onLogout()
                    }
                )
            }
        }
    }
}

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
                        popUpTo(Routes.HOME) {
                            saveState = true
                        }
                        launchSingleTop = true
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