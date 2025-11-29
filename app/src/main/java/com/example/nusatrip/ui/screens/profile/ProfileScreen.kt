package com.example.nusatrip.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nusatrip.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

// --- Constants (Encapsulated) ---
private val PrimaryColor = Color(0xFF762727)
private val DangerColor = Color(0xFFD32F2F)
private val BackgroundColor = Color(0xFFF8F8F8)
private val DividerColor = Color.LightGray.copy(alpha = 0.3f)

/**
 * Stateful Composable (Controller Layer).
 * Responsible for:
 * 1. State Hoisting (extracting state from the UI).
 * 2. interacting with the ViewModel.
 * 3. Handling business logic (e.g., reloading user data).
 *
 * @param authViewModel The ViewModel instance for authentication.
 * @param onLogout Callback function triggered when the user logs out.
 */
@Composable
fun ProfileRoute(
    authViewModel: AuthViewModel,
    onLogout: () -> Unit
) {
    val authState by authViewModel.authState.collectAsState()

    // Side Effect: Force reload user data to ensure the display name is up-to-date.
    // This addresses the issue where the name might be stale immediately after registration.
    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.reload()
    }

    val currentUser = authState.user ?: FirebaseAuth.getInstance().currentUser

    // Defensive Logic: Fallback strategy for the user's display name.
    // Priority: Display Name -> Email Username -> Default Placeholder.
    val safeName = currentUser?.displayName?.takeIf { it.isNotBlank() }
        ?: currentUser?.email?.substringBefore("@")
        ?: "NusaTrip User"

    val safeEmail = currentUser?.email ?: "Not Logged In"

    ProfileScreen(
        userName = safeName,
        userEmail = safeEmail,
        onLogoutClick = onLogout,
        onMenuClick = { menuOption ->
            // TODO: Implement navigation logic for specific menu items
        }
    )
}

/**
 * Stateless Composable (View Layer).
 * A pure UI component that renders based on the provided parameters.
 * This component is decoupled from the ViewModel, making it easy to preview and test.
 *
 * @param userName The name to display in the header.
 * @param userEmail The email to display in the header.
 * @param onLogoutClick Event handler for the logout button.
 * @param onMenuClick Event handler for menu item clicks.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String,
    onLogoutClick: () -> Unit,
    onMenuClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )
                    )
                },
            )
        },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // 1. Header Section
            ProfileHeader(name = userName, email = userEmail)

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Account Section
            MenuSection(
                sectionTitle = "ACCOUNT",
                menuItems = listOf("Profile", "Orders", "Smart Planner"),
                onItemClick = onMenuClick
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Support Section
            MenuSection(
                sectionTitle = "SUPPORT",
                menuItems = listOf("Help and Support", "About", "Report bugs"),
                onItemClick = onMenuClick
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 4. Logout Action
            LogoutButton(onClick = onLogoutClick)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// --- Private Helper Components ---

@Composable
private fun ProfileHeader(name: String, email: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Logic: Extract the first letter for the avatar, default to "U" if empty.
        val initial = if (name.isNotEmpty()) name.first().toString().uppercase() else "U"

        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E3C72)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initial,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun MenuSection(
    sectionTitle: String,
    menuItems: List<String>,
    onItemClick: (String) -> Unit
) {
    Text(
        text = sectionTitle,
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
    )
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Iterating through items to reduce boilerplate code (DRY Principle)
            menuItems.forEachIndexed { index, item ->
                ProfileMenuItem(title = item, onClick = { onItemClick(item) })

                // Add divider for all items except the last one
                if (index < menuItems.size - 1) {
                    HorizontalDivider(color = DividerColor, thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
private fun LogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = DangerColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Logout",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Logout",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}