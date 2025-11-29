package com.example.nusatrip.domain.model

import java.net.URI
import java.time.LocalDate

data class Plan (
    val startDate: LocalDate,
    val endDate: LocalDate,
    val title: String,
    val tags: List<String>,
    val imageUri: URI,
    val itinerary: Map<LocalDate, List<Itinerary>>
)