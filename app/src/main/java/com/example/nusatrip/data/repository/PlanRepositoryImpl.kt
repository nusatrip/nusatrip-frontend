package com.example.nusatrip.data.repository

import com.example.nusatrip.domain.model.Itinerary
import com.example.nusatrip.domain.model.Plan
import com.example.nusatrip.domain.repository.PlanRepository
import com.example.nusatrip.util.Resource
import com.example.nusatrip.util.Resource.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class PlanRepositoryImpl : PlanRepository {
    override fun getPlans(): Flow<Resource<List<Plan>>> {
        return flow {
            // Todo: Change this later
            emit(Resource.Loading)
            delay(2000)
            val data =  mutableListOf(
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
            emit(Resource.Success(data))
        }

    }

    override fun getPlan(id: UUID): Flow<Resource<Plan>> {
        return flow {
            emit(Resource.Loading)
            // Todo: Change this later
            delay(2000)
            val data = Plan(
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
            emit(Resource.Success(data))
        }

    }

    override fun createPlan(plan: Plan) {
        TODO("Not yet implemented")
    }

    override fun deletePlan(plan: Plan) {
        TODO("Not yet implemented")
    }

    override fun updatePlan(plan: Plan) {
        TODO("Not yet implemented")
    }
}