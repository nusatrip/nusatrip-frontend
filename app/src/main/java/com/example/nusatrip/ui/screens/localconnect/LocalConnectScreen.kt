package com.example.nusatrip.ui.screens.localconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nusatrip.R
import com.example.nusatrip.ui.screens.localconnect.components.*
import com.example.nusatrip.ui.screens.localconnect.models.*

@Composable
fun LocalConnectScreen(
    onNavigateToDetail: (String, LocalConnectType) -> Unit = { _, _ -> }
) {
    val scrollState = rememberScrollState()
    var selectedCity by remember { mutableStateOf("Pilih Lokasi") }
    val context = LocalContext.current

    val cities = listOf(
        "Yogyakarta",
        "Jakarta",
        "Bandung",
        "Surabaya",
        "Bali",
        "Semarang",
        "Malang",
        "Solo",
        "Medan",
        "Makassar"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Header Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.localconnect_image),
                contentDescription = "Local Culture Header",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Location Dropdown
        LocationDropdown(
            selectedCity = selectedCity,
            onCitySelected = {
                selectedCity = it
            },
            cities = cities,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = (-28).dp)
        )

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
                id = "business_1",
                name = "Bakpia Pathok 25",
                description = "Oleh oleh khas Jogja",
                hours = "09.00 - 20.00",
                address = "Jl. Mawar No.19",
                imageRes = R.drawable.localbussiness1
            ),
            LocalBusiness(
                id = "business_2",
                name = "Batik Winotosastro",
                description = "Batik tradisional Jogja",
                hours = "08.00 - 21.00",
                address = "Jl. Malioboro No.45",
                imageRes = R.drawable.localbussiness1
            ),
            LocalBusiness(
                id = "business_3",
                name = "Kerajinan Gerabah",
                description = "Kerajinan tanah liat",
                hours = "07.00 - 17.00",
                address = "Kasongan, Bantul",
                imageRes = R.drawable.localbussiness1
            )
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(localBusinesses) { business ->
                LocalBusinessCard(
                    business = business,
                    onClick = {
                        onNavigateToDetail(business.id, LocalConnectType.BUSINESS)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                id = "culinary_1",
                name = "Gudeg Yu Djum",
                description = "Kuliner khas Jogja",
                hours = "09.00 - 20.00",
                address = "Jl. Wijilan No.31",
                imageRes = R.drawable.localculinary1
            ),
            LocalCulinary(
                id = "culinary_2",
                name = "Sate Klathak Pak Pong",
                description = "Sate kambing khas Jogja",
                hours = "10.00 - 22.00",
                address = "Jl. Imogiri Timur",
                imageRes = R.drawable.localculinary1
            ),
            LocalCulinary(
                id = "culinary_3",
                name = "Angkringan Lik Man",
                description = "Angkringan legendaris",
                hours = "18.00 - 02.00",
                address = "Jl. Wongsodirjo",
                imageRes = R.drawable.localculinary1
            )
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(localCulinaries) { culinary ->
                LocalCulinaryCard(
                    culinary = culinary,
                    onClick = {
                        onNavigateToDetail(culinary.id, LocalConnectType.CULINARY)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                id = "tour_1",
                name = "Borobudur Tour",
                duration = "3 Hour Tour",
                imageRes = R.drawable.localtourguide1
            ),
            LocalTourGuide(
                id = "tour_2",
                name = "Prambanan Temple Tour",
                duration = "2 Hour Tour",
                imageRes = R.drawable.localtourguide1
            ),
            LocalTourGuide(
                id = "tour_3",
                name = "Merapi Jeep Lava Tour",
                duration = "4 Hour Tour",
                imageRes = R.drawable.localtourguide1
            )
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(localTourGuides) { tourGuide ->
                LocalTourGuideCard(
                    tourGuide = tourGuide,
                    onClick = {
                        onNavigateToDetail(tourGuide.id, LocalConnectType.TOUR_GUIDE)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview (showBackground = true)
@Composable
fun LocalConnectScreenPreview() {
    LocalConnectScreen()
}