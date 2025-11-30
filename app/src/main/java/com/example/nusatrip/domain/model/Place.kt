package com.example.nusatrip.domain.model

import com.example.nusatrip.R

data class ReviewPlace(
    val userName: String,
    val avatarRes: Int,
    val date: String,
    val rating: Double,
    val comment: String
)

data class Place(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    val rating: Double,
    val category: String,
    val imageRes: Int,
    val reviews: List<ReviewPlace> = emptyList()
)