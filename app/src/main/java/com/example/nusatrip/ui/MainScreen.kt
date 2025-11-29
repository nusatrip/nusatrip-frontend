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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nusatrip.ui.navigation.BottomNavItem
import com.example.nusatrip.ui.navigation.NavGraph
import com.example.nusatrip.ui.navigation.Routes

/**
 * The main entry point for the UI.
 * This component handles the global scaffold and conditional bottom navigation visibility.
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // 1. Observe the current back stack entry to determine the active route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // 2. Define which routes should display the Bottom Bar
    val bottomBarRoutes = listOf(
        Routes.HOME,
        Routes.LOCAL_CONNECT,
        Routes.SMART_PLANNER,
        Routes.PROFILE
    )

    // 3. Determine visibility based on the current route
    val shouldShowBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            NavGraph(
                navController = navController,
                startDestination = Routes.SPLASH
            )
        }
    }
}

/**
 * Private helper component for the Bottom Navigation Bar.
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
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
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