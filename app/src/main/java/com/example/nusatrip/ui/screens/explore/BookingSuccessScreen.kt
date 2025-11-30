package com.example.nusatrip.ui.screens.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nusatrip.R
import com.example.nusatrip.data.dummy.DummyData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BookingSuccessScreen(navController: NavController, placeId: Int) {
    val place = DummyData.getPlaceById(placeId)
    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

    Scaffold(
        topBar = {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 16.dp, start = 8.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Check Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFF8B3A3A), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Booking Successful", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Booking confirmed!", color = Color.Gray)
            Text("${place?.name} Entrance Ticket", color = Color.Gray)

            Spacer(modifier = Modifier.height(40.dp))

            // Invoice Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Invoice", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(24.dp))

                    InvoiceRow("Ticket Info", place?.name ?: "-")
                    Spacer(modifier = Modifier.height(16.dp))
                    InvoiceRow("Date", currentDate)
                    Spacer(modifier = Modifier.height(16.dp))
                    InvoiceRow("Guest", "1 Adults")

                    Spacer(modifier = Modifier.height(24.dp))

                    // Barcode Placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color.White)
                            .clip(RoundedCornerShape(4.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        // IMAGE BARCODE, tolong dibuatkan dulu le, masih placeholder text
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            repeat(30) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Black)
                                )
                                Spacer(modifier = Modifier.width(if(it % 3 == 0) 4.dp else 2.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InvoiceRow(label: String, value: String) {
    Column {
        Text(label, fontSize = 12.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}