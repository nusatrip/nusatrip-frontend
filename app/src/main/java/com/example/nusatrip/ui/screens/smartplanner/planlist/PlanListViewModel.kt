package com.example.nusatrip.ui.screens.smartplanner.planlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nusatrip.domain.model.Itinerary
import com.example.nusatrip.domain.model.Plan
import com.example.nusatrip.domain.repository.PlanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime

class PlanListViewModel(private val repo: PlanRepository) : ViewModel() {
    private var _isLoading: Boolean = false
    val isLoading get() = _isLoading

    private var _planList = MutableStateFlow<List<Plan>>(emptyList())
    val planList get() = _planList

    suspend fun loadData() {
        viewModelScope.launch {
            _isLoading = true;
            val result = repo.getPlans();
            _planList.value = result;
            _isLoading = false;
        }
    }
}