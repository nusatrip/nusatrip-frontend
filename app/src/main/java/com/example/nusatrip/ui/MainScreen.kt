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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nusatrip.ui.navigation.BottomNavItem
import com.example.nusatrip.ui.navigation.NavGraph
import com.example.nusatrip.ui.navigation.Routes

/**
 * The main entry point for the UI.
 * Handles Scaffolding, Bottom Navigation visibility, and Edge-to-Edge padding logic.
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define which routes need the Bottom Bar and standard Padding
    val mainTabRoutes = listOf(
        Routes.HOME,
        Routes.LOCAL_CONNECT,
        Routes.SMART_PLANNER,
        Routes.PROFILE
    )

    val showBottomBar = currentRoute in mainTabRoutes

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        val targetModifier = if (showBottomBar) {
            Modifier.padding(paddingValues)
        } else {
            Modifier
        }

        NavGraph(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = targetModifier
        )
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
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
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
                    Icon(imageVector = item.icon, contentDescription = item.label)
                },
                label = {
                    Text(text = item.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}