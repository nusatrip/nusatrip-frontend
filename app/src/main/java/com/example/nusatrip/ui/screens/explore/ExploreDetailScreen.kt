package com.example.nusatrip.ui.screens.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nusatrip.data.dummy.DummyData
import com.example.nusatrip.domain.model.ReviewPlace

@Composable
fun ExploreDetailScreen(navController: NavController, placeId: Int) {
    val place = DummyData.getPlaceById(placeId)
    val primaryColor = Color(0xFF8B3A3A)

    if (place != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image (Header)
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )

            // Tombol Back
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 40.dp, start = 16.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF8B3A3A)
                )
            }

            // Content Body (Sheet Putih)
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 300.dp), // Overlap sedikit dengan gambar
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Judul Tempat
                    Text(
                        text = place.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    // Lokasi
                    Text(
                        text = place.location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Overview, 1 = Reviews

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        TabItem(title = "Overview", isSelected = selectedTab == 0) { selectedTab = 0 }
                        TabItem(title = "Reviews", isSelected = selectedTab == 1) { selectedTab = 1 }
                    }

                    Divider(color = Color.LightGray.copy(alpha = 0.5f))

                    Spacer(modifier = Modifier.height(16.dp))

                    // Isi Tab
                    Box(modifier = Modifier.weight(1f)) {
                        if (selectedTab == 0) {
                            // Overview Content
                            Text(
                                text = place.description,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 24.sp,
                                color = Color.DarkGray
                            )
                        } else {
                            // Reviews Content
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(place.reviews) { review ->
                                    ReviewItem(review)
                                }
                            }
                        }
                    }

                    // Tombol Booking
                    Button(
                        onClick = { navController.navigate("booking_success/${place.id}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A3A)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Book Ticket", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    } else {
        // Error handling jika ID tidak ditemukan
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Place not found")
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() }
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = title,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.Black else Color.Gray
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(Color(0xFF8B3A3A), RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
fun ReviewItem(review: ReviewPlace) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            // Avatar Placeholder
            Image(
                painter = painterResource(id = review.avatarRes),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = review.userName, fontWeight = FontWeight.Bold)
                    Text(text = review.date, fontSize = 12.sp, color = Color.Gray)
                }
                Text(text = review.comment, fontSize = 14.sp, modifier = Modifier.padding(vertical = 4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = review.rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}