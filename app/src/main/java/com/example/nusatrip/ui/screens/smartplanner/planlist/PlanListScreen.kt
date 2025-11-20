package com.example.nusatrip.ui.screens.smartplanner.planlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController // Import NavController
import androidx.navigation.compose.rememberNavController // Import for preview
import com.example.nusatrip.R

@Composable
fun PlanListScreen(
    navController: NavController // The only argument is now NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top App Bar
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

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            // Plan Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .clickable {
                        // Navigate to the itinerary screen when the card is tapped
                        navController.navigate("itinerary")
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column {
                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.smartplanner_banner),
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
                            text = "Jogja Trip",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1F1F1F)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Sept 19 - Sept 30, 2025",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF666666)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Family Chip aligned to the right
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                border = BorderStroke(1.dp, Color(0xFFD1D5DB)),
                                color = Color.Transparent
                            ) {
                                Text(
                                    text = "Family",
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF4B5563)
                                )
                            }
                        }
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


// Preview
@Preview(showBackground = true)
@Composable
fun PlannerScreenPreview() {
    // For the preview, create a NavController instance.
    PlanListScreen(navController = rememberNavController())
}