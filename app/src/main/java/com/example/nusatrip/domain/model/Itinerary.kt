package com.example.nusatrip.domain.model

import java.time.LocalDateTime

data class Itinerary (
    val dateTime: LocalDateTime,
    val title: String,
    val description: String
)