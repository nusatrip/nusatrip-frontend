package com.example.nusatrip.data.model

import java.time.LocalDateTime

data class Itinerary (
    val dateTime: LocalDateTime,
    val title: String,
    val description: String
)