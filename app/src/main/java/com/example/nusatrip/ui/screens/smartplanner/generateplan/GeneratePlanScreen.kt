package com.example.nusatrip.ui.screens.smartplanner.generateplan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.SelectableChip
import com.example.nusatrip.ui.components.ActivableChip
import com.example.nusatrip.ui.components.DateRangePicker
import com.example.nusatrip.ui.navigation.Routes
import java.time.LocalDate

@Composable
fun GeneratePlanScreen(navController: NavController) {
    val viewModel: GeneratePlanViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            SmartPlannerTopBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            // Travel Destination
            SectionTitle("Travel Destinition")
            Spacer(modifier = Modifier.height(8.dp))
            DestinationField(
                value = state.destinations.joinToString(),
                onValueChange = { value -> viewModel.updateDestinations(value.split(", ")) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Travel Dates
            SectionTitle("Travel Dates")
            Spacer(modifier = Modifier.height(12.dp))
            CalendarSection(
                onDateSelected = { startDate, endDate -> {
                    viewModel.updateStartDate(startDate)
                    viewModel.updateEndDate(endDate)
                } }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Activity Preferences
            SectionTitle("Activity Preferences")
            Spacer(modifier = Modifier.height(12.dp))
            ActivityChips(
                activities = listOf("Nature Exploration", "History & culture", "Culinary", "Shopping", "Family"),
                selectedActivities = state.activities.toSet(),
                onActivityToggle = { activity ->
                    viewModel.updateActivities(
                        if (state.activities.contains(activity)) {
                            state.activities - activity
                        } else {
                            state.activities + activity
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Travel Budget
            SectionTitle("Travel Budget")
            Spacer(modifier = Modifier.height(8.dp))
            BudgetField(
                value = state.budget ?: 0,
                onValueChange = { value -> viewModel.updateBudget(value.toIntOrNull()) }
            );

            Spacer(modifier = Modifier.height(24.dp))

            // Travel Style
            SectionTitle("Travel Style")
            Spacer(modifier = Modifier.height(12.dp))
            TravelStyleChips(
                styles = listOf("Solo Traveler", "Romantic couple", "Family with children", "Backpacker", "Luxury Traveler"),
                selectedStyle = state.travelStyle ?: "Solo Traveler",
                onStyleSelected = { viewModel.updateTravelStyle(it) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Activity Intensity
            SectionTitle("Activity Intensity")
            Spacer(modifier = Modifier.height(12.dp))
            IntensityChips(
                intensities = listOf("Relaxed", "Balanced", "Full"),
                selectedIntensity = state.intensity ?: "Relaxed",
                onIntensitySelected = { viewModel.updateIntensity(it) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Generate Button
            Button(
                onClick = { navController.navigate(Routes.ITINERARY) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7C2D12)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Generate Plan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartPlannerTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Smart Planner",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF1E3A8A)
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF374151)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF374151)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = "Ex. Malang, Bali",
                color = Color(0xFF9CA3AF)
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFD1D5DB),
            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Color(0xFF4B5563)
            )
        },
        singleLine = true
    )
}

@Composable
fun CalendarSection(
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFFF9FAFB)
    ) {
        DateRangePicker(onDateChange = onDateSelected)
    }
}

@Composable
fun ActivityChips(
    activities: List<String>,
    selectedActivities: Set<String>,
    onActivityToggle: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        activities.forEach { activity ->
            val isSelected = selectedActivities.contains(activity)
            ActivableChip(
                isSelected = isSelected,
                onClick = { onActivityToggle(activity) },
                label = activity
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetField(
    value: Int,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFD1D5DB),
            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        singleLine = true
    )
}

@Composable
fun TravelStyleChips(
    styles: List<String>,
    selectedStyle: String,
    onStyleSelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        styles.forEach { style ->
            val isSelected = selectedStyle == style
            ActivableChip(
                isSelected = isSelected,
                onClick = {onStyleSelected(style)},
                label = style
            )
        }
    }
}

@Composable
fun IntensityChips(
    intensities: List<String>,
    selectedIntensity: String,
    onIntensitySelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        intensities.forEach { intensity ->
            val isSelected = selectedIntensity == intensity
            ActivableChip(
                isSelected = isSelected,
                onClick = {onIntensitySelected(intensity)},
                label = intensity
            )
        }
    }
}