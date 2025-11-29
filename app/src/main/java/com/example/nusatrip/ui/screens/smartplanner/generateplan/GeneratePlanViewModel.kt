package com.example.nusatrip.ui.screens.smartplanner.generateplan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.util.Date

data class GeneratePlanState(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val activities: List<String> = emptyList(),
    val travelStyle: String? = null,
    val destinations: List<String> = emptyList(),
    val intensity: String? = null,
    val budget: Int? = null,
)

class GeneratePlanViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<GeneratePlanState>(GeneratePlanState())
    val uiState = _uiState.asStateFlow()

    // 1. Update startDate
    fun updateStartDate(date: LocalDate?) {
        _uiState.value = _uiState.value.copy(startDate = date)
    }

    // 2. Update endDate
    fun updateEndDate(date: LocalDate?) {
        _uiState.value = _uiState.value.copy(endDate = date)
    }

    // 3. Update activities (replace entire list)
    fun updateActivities(activities: List<String>) {
        _uiState.value = _uiState.value.copy(activities = activities)
    }

    // 4. Add single activity
    fun addActivity(activity: String) {
        _uiState.value = _uiState.value.copy(
            activities = _uiState.value.activities + activity
        )
    }

    // 5. Remove activity at index
    fun removeActivity(index: Int) {
        _uiState.value = _uiState.value.copy(
            activities = _uiState.value.activities.toMutableList().apply { removeAt(index) }
        )
    }

    // 6. Update travelStyle
    fun updateTravelStyle(style: String?) {
        _uiState.value = _uiState.value.copy(travelStyle = style)
    }

    // 7. Update destinations (replace entire list)
    fun updateDestinations(destinations: List<String>) {
        _uiState.value = _uiState.value.copy(destinations = destinations)
    }

    // 8. Add single destination
    fun addDestination(destination: String) {
        _uiState.value = _uiState.value.copy(
            destinations = _uiState.value.destinations + destination
        )
    }

    // 9. Remove destination at index
    fun removeDestination(index: Int) {
        _uiState.value = _uiState.value.copy(
            destinations = _uiState.value.destinations.toMutableList().apply { removeAt(index) }
        )
    }

    // 10. Update budget
    fun updateBudget(budget: Int?) {
        _uiState.value = _uiState.value.copy(budget = budget)
    }

    fun updateIntensity(intensity: String?) {
        _uiState.value = _uiState.value.copy(intensity = intensity)
    }

    // 11. Reset all state
    fun resetState() {
        _uiState.value = GeneratePlanState()
    }
}