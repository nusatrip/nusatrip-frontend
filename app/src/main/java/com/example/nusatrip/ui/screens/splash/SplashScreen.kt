package com.example.nusatrip_papb.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nusatrip_papb.R
import com.example.nusatrip_papb.ui.navigation.Routes
import kotlinx.coroutines.delay

/**
 * Initial splash screen with logo animation
 */
@Composable
fun SplashScreen(navController: NavController) {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
        delay(2000)
        navController.navigate(Routes.ONBOARDING) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8B3A3A)),
        contentAlignment = Alignment.Center
    ) {
        // Single logo image (logo + text combined)
        Image(
            painter = painterResource(id = R.drawable.nusatrip_logo_text),
            contentDescription = "NusaTrip Logo",
            modifier = Modifier
                .size(350.dp)
                .alpha(alpha.value)
        )
    }
}