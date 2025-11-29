package com.example.nusatrip.ui.screens.smartplanner.planlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.nusatrip.data.repository.PlanRepositoryImpl
import com.example.nusatrip.domain.model.Plan
import com.example.nusatrip.ui.components.TagChip
import com.example.nusatrip.ui.navigation.Routes
import com.example.nusatrip.util.Resource
import java.time.format.DateTimeFormatter

@Composable
fun PlanListScreen(
    navController: NavController,
) {
    val repo = PlanRepositoryImpl()
    val viewModel: PlanListViewModel = viewModel(
        factory = PlanListViewModelFactory(repo)
    )

    val state by viewModel.uiState.collectAsState()

    when (state) {
        is Resource.Error -> {
            Text("Error")
        }
        Resource.Loading -> {
            Text("Loading")
        }
        is Resource.Success -> {
            val planList = (state as Resource.Success).data
            SuccessView(navController = navController, data = planList)
        }
    }
}

@Composable
private fun SuccessView(navController: NavController,data: List<Plan>) {
    Scaffold(
        topBar = { TopBar(navController) }
    ) { padding -> // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")

            if (data.isNotEmpty()) {
                LazyColumn {
                    items(items = data) { item ->
                        PlanCard(
                            title = item.title,
                            startDateText = item.startDate.format(dateFormatter),
                            endDateText = item.endDate.format(dateFormatter),
                            tags = item.tags,
                            coverUrl = item.imageUri.toString(),
                            onClick = { navController.navigate(Routes.ITINERARY) })
                    }
                }
            }


            // Generate New Plan Button
            Button(
                onClick = {
                    // TODO: Implement your "Generate New Plan" logic here.
                    // This might involve calling a ViewModel function or showing a dialog.
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7F1D1D)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Generate New Plan",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun PlanCard(
    title: String,
    startDateText: String,
    endDateText: String,
    tags: List<String>,
    coverUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Image
            AsyncImage(
                model = coverUrl,
                contentDescription = "Trip Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            // Card Content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1F1F1F)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$startDateText - $endDateText",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Family Chip aligned to the right
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    tags.forEach { text -> TagChip(text) }
                }
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF1F1F1F)
            )
        }

        Text(
            text = "Planner",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1F1F1F)
        )

        Spacer(modifier = Modifier.width(48.dp))
    }
}


// Preview
@Preview(showBackground = true)
@Composable
fun PlannerScreenPreview() {
    // For the preview, create a NavController instance.
    PlanListScreen(navController = rememberNavController())
}