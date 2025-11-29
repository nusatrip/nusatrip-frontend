package com.example.nusatrip.ui.screens.localconnect.models

data class LocalBusiness(
    val id: String = "",
    val name: String,
    val description: String,
    val hours: String,
    val address: String,
    val imageRes: Int
)

data class LocalCulinary(
    val id: String = "",
    val name: String,
    val description: String,
    val hours: String,
    val address: String,
    val imageRes: Int
)

data class LocalTourGuide(
    val id: String = "",
    val name: String,
    val duration: String,
    val imageRes: Int
)

data class Review(
    val userName: String,
    val userImageRes: Int,
    val comment: String,
    val rating: Float,
    val timeAgo: String
)

data class LocalConnectDetail(
    val id: String,
    val name: String,
    val location: String,
    val imageRes: Int,
    val overviewText: String,
    val reviews: List<Review>,
    val totalReviews: Int,
    val type: LocalConnectType
)

enum class LocalConnectType {
    BUSINESS,
    CULINARY,
    TOUR_GUIDE
}