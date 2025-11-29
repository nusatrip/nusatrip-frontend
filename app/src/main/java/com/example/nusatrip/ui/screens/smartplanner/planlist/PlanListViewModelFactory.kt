package com.example.nusatrip.ui.screens.smartplanner.planlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nusatrip.domain.repository.PlanRepository

class PlanListViewModelFactory(private val repo: PlanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanListViewModel(repo) as T
    }
}