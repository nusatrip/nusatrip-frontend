package com.example.nusatrip.data.repository

import com.example.nusatrip.domain.model.Itinerary
import com.example.nusatrip.domain.model.Plan
import com.example.nusatrip.domain.repository.PlanRepository
import kotlinx.coroutines.delay
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class PlanRepositoryImpl : PlanRepository {
    override suspend fun getPlans(): List<Plan> {
        // Todo: Change this later
        delay(2000)
        return mutableListOf(
            Plan(
                id = UUID.randomUUID(),
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
                id = UUID.randomUUID(),
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
                id = UUID.randomUUID(),
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

    override suspend fun getPlan(id: UUID): Plan {
        // Todo: Change this later
        delay(2000)
        return Plan(
            id = UUID.randomUUID(),
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
    }

    override suspend fun createPlan(plan: Plan) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePlan(plan: Plan) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePlan(plan: Plan) {
        TODO("Not yet implemented")
    }
}