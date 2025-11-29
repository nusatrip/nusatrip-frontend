package com.example.nusatrip.domain.repository

import com.example.nusatrip.domain.model.Plan
import com.example.nusatrip.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import kotlin.uuid.Uuid

interface PlanRepository {
    fun getPlans(): Flow<Resource<List<Plan>>>
    fun getPlan(id: UUID): Flow<Resource<Plan>>
    fun createPlan(plan: Plan)
    fun deletePlan(plan: Plan)
    fun updatePlan(plan: Plan)
}