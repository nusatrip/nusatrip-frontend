package com.example.nusatrip.ui.navigation

/**
 * Object containing all navigation route constants
 * Use Routes.LOGIN, Routes.HOME, etc. for navigation
 */
object Routes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val MAIN = "main"
    const val HOME = "home"
    const val LOCAL_CONNECT = "localconnect"
    const val LOCAL_CONNECT_DETAIL = "localconnect/detail/{detailId}/{detailType}"
    const val SMART_PLANNER = "smartplanner"
    const val GENERATE_PLAN = "generate_plan"
    const val PROFILE = "profile"
    const val ITINERARY = "itinerary"

    /**
     * Helper function to create detail route with parameters
     * @param detailId The ID of the item
     * @param detailType The type of item (BUSINESS, CULINARY, TOUR_GUIDE)
     * @return Formatted route string
     */
    fun localConnectDetail(detailId: String, detailType: String): String {
        return "localconnect/detail/$detailId/$detailType"
    }
    const val  EXPLORE_PAGE = "explore"
}