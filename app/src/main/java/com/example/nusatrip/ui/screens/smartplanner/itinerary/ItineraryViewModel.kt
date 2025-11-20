package com.example.nusatrip.ui.screens.smartplanner.itinerary

import androidx.lifecycle.ViewModel
import com.example.nusatrip.domain.model.Itinerary
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime

class ItineraryViewModel : ViewModel() {
    private var _title: String? = null
    val title get() = _title

    private var _coverUri: URI? = null
    val coverUri get() = _coverUri

    private var _startDate: LocalDate? = null
    val startDate get() = _startDate

    private var _endDate: LocalDate? = null
    val endDate get() = _endDate

    private var _itinerary: Map<LocalDate, List<Itinerary>>? = null
    val itinerary get() = _itinerary

    private var _tags: List<String>? = null
    val tags get() = _tags

    init {
        _title = "Jogja Trip"
        _coverUri = URI("https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/222/2025/01/15/WhatsApp-Image-2024-12-31-at-152310-1709618523.jpeg")
        _startDate = LocalDate.of(2025, 9, 19)
        _endDate = LocalDate.of(2025, 9, 20)
        _itinerary = mapOf(
            LocalDate.of(2025, 9, 19) to listOf(
                Itinerary(
                    title = "Prambanan Temple",
                    description = "Prambanan Temple is a majestic 9th-century Hindu temple complex located in Yogyakarta, Indonesia. ",
                    dateTime = LocalDateTime.of(2025, 9, 19, 9, 0)
                ),
                Itinerary(
                    title = "Prambanan Temple",
                    description = "Prambanan Temple is a majestic 9th-century Hindu temple complex located in Yogyakarta, Indonesia. ",
                    dateTime = LocalDateTime.of(2025, 9, 19, 15, 0)
                ),
                Itinerary(
                    title = "Prambanan Temple",
                    description = "Prambanan Temple is a majestic 9th-century Hindu temple complex located in Yogyakarta, Indonesia. ",
                    dateTime = LocalDateTime.of(2025, 9, 20, 9, 0)
                ),
            )

        )
        _tags = listOf("Family")
    }
}