package com.example.nusatrip.ui.screens.smartplanner.planlist

import androidx.lifecycle.ViewModel
import com.example.nusatrip.domain.model.Itinerary
import com.example.nusatrip.domain.model.Plan
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime

class PlanListViewModel : ViewModel() {
    private var _isLoading: Boolean = false
    val isLoading get() = _isLoading

    private var _planList: List<Plan>? = null
    val planList get() = _planList

    init {
        _isLoading = false
        _planList = mutableListOf(
            Plan(
                title = "Jogja trip",
                startDate = LocalDate.of(2025, 9, 19),
                endDate = LocalDate.of(2025, 9, 20),
                imageUri = URI("https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/222/2025/01/15/WhatsApp-Image-2024-12-31-at-152310-1709618523.jpeg"),
                tags = listOf("Family"),
                itinerary = mapOf(
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
            ),
            Plan(
                title = "Bali trip",
                startDate = LocalDate.of(2025, 9, 19),
                endDate = LocalDate.of(2025, 9, 20),
                imageUri = URI("https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/222/2025/01/15/WhatsApp-Image-2024-12-31-at-152310-1709618523.jpeg"),
                tags = listOf("Family"),
                itinerary = mapOf(
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
            ),
            Plan(
                title = "Banda Neira trip",
                startDate = LocalDate.of(2025, 9, 19),
                endDate = LocalDate.of(2025, 9, 20),
                imageUri = URI("https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/222/2025/01/15/WhatsApp-Image-2024-12-31-at-152310-1709618523.jpeg"),
                tags = listOf("Family"),
                itinerary = mapOf(
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
            )
        )
    }
}