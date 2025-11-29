package com.example.nusatrip.domain.repository

import com.example.nusatrip.domain.model.Plan
import java.util.UUID
import kotlin.uuid.Uuid

interface PlanRepository {
    suspend fun getPlans(): List<Plan>
    suspend fun getPlan(id: UUID): Plan
    suspend fun createPlan(plan: Plan)
    suspend fun deletePlan(plan: Plan)
    suspend fun updatePlan(plan: Plan)
}