package com.example.nusatrip.ui.screens.localconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nusatrip.R
import com.example.nusatrip.ui.screens.localconnect.components.OverviewTab
import com.example.nusatrip.ui.screens.localconnect.components.ReviewsTab
import com.example.nusatrip.domain.model.LocalConnectDetail
import com.example.nusatrip.domain.model.LocalConnectType
import com.example.nusatrip.domain.model.Review

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalConnectDetailScreen(
    detailId: String,
    detailType: String,
    onBackClick: () -> Unit = {},
    onRouteClick: () -> Unit = {}
) {
    val detailData = getSampleDetailData(detailId, detailType)

    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Overview", "Reviews")

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-56).dp)
            ) {
                Image(
                    painter = painterResource(id = detailData.imageRes),
                    contentDescription = detailData.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Route Button
                Button(
                    onClick = onRouteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A3A)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_dialog_map),
                        contentDescription = "Route",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Route", color = Color.White)
                }
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-56).dp)
            ) {
                // Title and Location
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = detailData.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = detailData.location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                // Tab Row
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.White,
                    contentColor = Color(0xFF8B3A3A),
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = Color(0xFF8B3A3A),
                            height = 3.dp
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    text = title,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            },
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }

                // Tab Content
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5F5))
                ) {
                    when (selectedTab) {
                        0 -> OverviewTab(detailData.overviewText)
                        1 -> ReviewsTab(detailData.reviews, detailData.totalReviews)
                    }
                }
            }
        }
    }
}

// Helper functions
private fun getSampleDetailData(detailId: String, detailType: String): LocalConnectDetail {
    return when (detailType) {
        "BUSINESS" -> getBusinessDetail(detailId)
        "CULINARY" -> getCulinaryDetail(detailId)
        "TOUR_GUIDE" -> getTourGuideDetail(detailId)
        else -> getBusinessDetail(detailId)
    }
}

private fun getBusinessDetail(detailId: String): LocalConnectDetail {
    return LocalConnectDetail(
        id = detailId,
        name = "Bakpia Pathok 25",
        location = "Kab. Bantul, D.I. Yogyakarta",
        imageRes = R.drawable.localbussiness1,
        overviewText = """
            Bakpia Pathok is a well-known traditional snack from Yogyakarta, Indonesia. These small, round pastries are typically filled with sweet mung bean paste and wrapped in a soft, flaky dough. The combination of the slightly crispy outer layer and the smooth, sweet filling makes Bakpia a favorite treat among locals and tourists alike.
            
            Today, Bakpia Pathok comes in a variety of modern flavors such as chocolate, cheese, green tea, and even durian.
        """.trimIndent(),
        reviews = getSampleReviews(),
        totalReviews = 900,
        type = LocalConnectType.BUSINESS
    )
}

private fun getCulinaryDetail(detailId: String): LocalConnectDetail {
    return LocalConnectDetail(
        id = detailId,
        name = "Gudeg Yu Djum",
        location = "Jl. Wijilan No. 31, Yogyakarta",
        imageRes = R.drawable.localculinary1,
        overviewText = """
            Gudeg Yu Djum adalah salah satu warung gudeg paling terkenal di Yogyakarta. Dengan resep turun-temurun sejak tahun 1950-an, gudeg ini memiliki cita rasa manis yang khas dengan tekstur nangka yang lembut. 
            
            Disajikan dengan nasi, ayam kampung, telur, krecek, dan sambal goreng krecek, Gudeg Yu Djum menjadi pilihan favorit wisatawan dan warga lokal.
        """.trimIndent(),
        reviews = getSampleReviews(),
        totalReviews = 1250,
        type = LocalConnectType.CULINARY
    )
}

private fun getTourGuideDetail(detailId: String): LocalConnectDetail {
    return LocalConnectDetail(
        id = detailId,
        name = "Borobudur Tour",
        location = "Borobudur Temple, Magelang",
        imageRes = R.drawable.localtourguide1,
        overviewText = """
            Experience the magnificent Borobudur Temple, the world's largest Buddhist monument and UNESCO World Heritage Site. This 3-hour guided tour will take you through the rich history and intricate stone carvings of this 9th-century masterpiece. 
            
            Our certified guide will explain the symbolism behind each level of the temple, from the base representing the world of desire to the top symbolizing the world of formlessness. Includes sunrise viewing option.
        """.trimIndent(),
        reviews = getSampleReviews(),
        totalReviews = 2100,
        type = LocalConnectType.TOUR_GUIDE
    )
}

private fun getSampleReviews(): List<Review> {
    return listOf(
        Review(
            userName = "Nadya",
            userImageRes = R.drawable.localbussiness1,
            comment = "Amazing experience! Highly recommended for everyone visiting Yogyakarta.",
            rating = 5.0f,
            timeAgo = "6 months ago"
        ),
        Review(
            userName = "Raihani",
            userImageRes = R.drawable.localbussiness1,
            comment = "Great service and very authentic. Will definitely come back!",
            rating = 5.0f,
            timeAgo = "6 months ago"
        ),
        Review(
            userName = "Echa",
            userImageRes = R.drawable.localbussiness1,
            comment = "Worth every penny. The guide was very knowledgeable and friendly.",
            rating = 5.0f,
            timeAgo = "6 months ago"
        ),
        Review(
            userName = "Ghaniya",
            userImageRes = R.drawable.localbussiness1,
            comment = "Perfect! Can't wait to visit again with my family.",
            rating = 5.0f,
            timeAgo = "6 months ago"
        )
    )
}

@Preview
@Composable
fun LocalConnectDetailScreenPreview() {
    LocalConnectDetailScreen(detailId = "business_1", detailType = "BUSINESS")
}
