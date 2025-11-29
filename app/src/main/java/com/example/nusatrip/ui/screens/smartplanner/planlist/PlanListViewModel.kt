package com.example.nusatrip.ui.screens.smartplanner.planlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nusatrip.domain.model.Itinerary
import com.example.nusatrip.domain.model.Plan
import com.example.nusatrip.domain.repository.PlanRepository
import com.example.nusatrip.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime

class PlanListViewModel(private val repo: PlanRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<Resource<List<Plan>>>(Resource.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getPlans().collectLatest {
                state -> _uiState.value = state
            }
        }
    }
}