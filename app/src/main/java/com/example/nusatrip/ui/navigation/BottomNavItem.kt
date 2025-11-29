package com.example.nusatrip.ui.navigation

import androidx.annotation.DrawableRes

/**
 * Data class representing a navigation item in the Bottom Bar.
 *
 * @property route The unique navigation route string.
 * @property label The accessibility label for the item.
 * @property iconRes The drawable resource ID for the icon.
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int
)