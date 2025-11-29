package com.example.nusatrip.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActivableChip(
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    label: String,
) {
    FilterChip(
        selected = isSelected,
        onClick = { onClick() },
        label = {
            Text(
                text = label,
                fontSize = 14.sp
            )
        },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFF8B3A3A),
            selectedLabelColor = Color.White,
            containerColor = Color.White,
            labelColor = Color(0xFF374151)
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = if (isSelected) Color(0xFF2563EB) else Color(0xFFD1D5DB),
            selectedBorderColor = Color(0xFF8B3A3A),
            borderWidth = 1.dp,
            selectedBorderWidth = 1.dp
        )
    )
}