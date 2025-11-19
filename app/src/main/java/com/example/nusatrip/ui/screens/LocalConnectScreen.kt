@file:Suppress("DEPRECATION")
package com.example.nusatrip.ui.screens.localconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
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
import com.example.nusatrip.R
import com.google.accompanist.pager.ExperimentalPagerApi

data class LocalBusiness(
    val name: String,
    val description: String,
    val hours: String,
    val address: String,
    val imageRes: Int
)

data class LocalCulinary(
    val name: String,
    val description: String,
    val hours: String,
    val address: String,
    val imageRes: Int
)

data class LocalTourGuide(
    val name: String,
    val duration: String,
    val imageRes: Int
)

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun LocalConnectScreen() {
    val scrollState = rememberScrollState()
    var selectedCity by remember { mutableStateOf("Yogyakarta") }
    var expandedDropdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Header Image with Cultural Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.localconnect_image), // Ganti dengan gambar budaya Papua
                contentDescription = "Local Culture Header",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Location Dropdown Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = (-28).dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFFE57373),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = selectedCity,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                IconButton(
                    onClick = { expandedDropdown = !expandedDropdown },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = Color(0xFFE57373)
                    )
                }
            }
        }

        // Local Business Section
        Text(
            text = "Local Business",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF10367D),
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        val localBusinesses = listOf(
            LocalBusiness(
                "Bakpia Pathok 25",
                "Oleh oleh khas Jogja",
                "09.00 - 20.00",
                "Jl. Mawar No. 19",
                R.drawable.localbussiness1
            )
        )

        localBusinesses.forEach { business ->
            LocalBusinessCard(business)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Local Culinary Section
        Text(
            text = "Local Culinary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF10367D),
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        val localCulinaries = listOf(
            LocalCulinary(
                "Gudheg Yu Djum",
                "Kuliner khas Jogja",
                "09.00 - 20.00",
                "Jl. Mawar No. 19",
                R.drawable.localculinary1
            )
        )

        localCulinaries.forEach { culinary ->
            LocalCulinaryCard(culinary)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Local Tour Guide Section
        Text(
            text = "Local Tour Guide",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF10367D),
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        val localTourGuides = listOf(
            LocalTourGuide(
                "Borobudur Tour",
                "3 Hour Tour",
                R.drawable.localtourguide1
            )
        )

        localTourGuides.forEach { tourGuide ->
            LocalTourGuideCard(tourGuide)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun LocalBusinessCard(business: LocalBusiness) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = business.imageRes),
                contentDescription = business.name,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ){
                Column (modifier = Modifier.weight(1f)){
                    Text(
                        text = business.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = business.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = business.hours,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = business.address,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Button(
                    onClick = { /* TODO: Detail action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A3A)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(28.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Detail",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun LocalCulinaryCard(culinary: LocalCulinary) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = culinary.imageRes),
                contentDescription = culinary.name,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = culinary.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = culinary.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = culinary.hours,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = culinary.address,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Button(
                    onClick = { /* TODO: Detail action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A3A)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(28.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Detail",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun LocalTourGuideCard(tourGuide: LocalTourGuide) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = tourGuide.imageRes),
                contentDescription = tourGuide.name,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = tourGuide.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = tourGuide.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Button(
                    onClick = { /* TODO: Detail action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A3A)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(28.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Detail",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}