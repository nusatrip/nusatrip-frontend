package com.example.nusatrip.ui.screens.smartplanner.itinerary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.nusatrip.ui.components.TagChip
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleItem(timeText: String, title: String, description: String) {

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row {
            Text(
                text = timeText,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF666666)
                ),
                modifier = Modifier.width(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF666666)
                    )
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun ItineraryScreen(
    viewModel: ItineraryViewModel = viewModel(),
    navController: NavController
) {
    Column {
        Box {
            // Background image
            AsyncImage(
                model = viewModel.coverUri.toString(),
                contentDescription = "Destination Image",
                modifier = Modifier.fillMaxWidth().height(240.dp),
                contentScale = ContentScale.Crop
            )

            // Back button
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            ItineraryCard(
                title = viewModel.title,
                startDate = viewModel.startDate,
                endDate = viewModel.endDate,
                tags = viewModel.tags
            )

            // Itinerary content
            val dateFormatter = DateTimeFormatter.ofPattern("d MMM, yyy")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                viewModel.itinerary?.entries?.withIndex()?.forEach { (i, item) ->
                    Column {
                        Text(
                            text = "Day ${i + 1} - ${item.key.format(dateFormatter)}",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF666666)
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        item.value.forEach { itinerary ->
                            ScheduleItem(
                                timeText = itinerary.dateTime.format(
                                    timeFormatter
                                ), title = itinerary.title, itinerary.description
                            )
                        }

                    }
                }

            }
        }
    }


}

@Composable
fun ItineraryCard(title: String?, startDate: LocalDate?, endDate: LocalDate?, tags: List<String>?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-60).dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)

    ) {
        Text(
            text = title ?: "No data",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF10367D)
            )
        )

        val formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy")
        val startDate = startDate?.format(formatter)
        val endDate = endDate?.format(formatter)

        Text(
            text = "$startDate - $endDate",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            tags?.forEach { text -> TagChip(text) }
        }
    }
}

// Preview annotations
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ItineraryScreen(navController = rememberNavController())
}