package com.example.nusatrip.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DateRangePicker(
    modifier: Modifier = Modifier,
    onDateChange: (LocalDate?, LocalDate?) -> Unit
) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Month/Year navigation
        MonthNavigation(
            currentDate = currentDate,
            onPreviousMonth = { currentDate = currentDate.minusMonths(1) },
            onNextMonth = { currentDate = currentDate.plusMonths(1) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weekday headers (SUN-SAT)
        WeekdayHeaders()

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar grid with range selection
        CalendarGrid(
            currentDate = currentDate,
            startDate = startDate,
            endDate = endDate,
            onDateSelected = { day ->
                val selectedDate = LocalDate.of(currentDate.year, currentDate.month, day)

                when {
                    startDate == null -> {
                        startDate = selectedDate
                        endDate = null
                    }
                    endDate == null -> {
                        if (selectedDate.isBefore(startDate)) {
                            startDate = selectedDate
                            endDate = null
                        } else {
                            endDate = selectedDate
                        }
                    }
                    else -> {
                        startDate = selectedDate
                        endDate = null
                    }
                }

                onDateChange(startDate, endDate)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display selected range
        SelectedRangeText(startDate, endDate)
    }
}

@Composable
private fun MonthNavigation(
    currentDate: LocalDate,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous month")
        }

        Text(
            text = "${currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentDate.year}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = onNextMonth) {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next month")
        }
    }
}

@Composable
private fun WeekdayHeaders() {
    val weekDays = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        weekDays.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun CalendarGrid(
    currentDate: LocalDate,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onDateSelected: (Int) -> Unit
) {
    val yearMonth = YearMonth.of(currentDate.year, currentDate.month)
    val daysInMonth = yearMonth.lengthOfMonth()
    // Convert from Monday=1..Sunday=7 to Sunday=0..Saturday=6
    val firstDayOfWeek = LocalDate.of(currentDate.year, currentDate.month, 1).dayOfWeek.value % 7

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        var currentDay = 1
        for (week in 0..5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (dayOfWeek in 0..6) {
                    val isEmptyCell = week == 0 && dayOfWeek < firstDayOfWeek
                    val isDayCell = !isEmptyCell && currentDay <= daysInMonth

                    if (isDayCell) {
                        val day = currentDay
                        val date = LocalDate.of(currentDate.year, currentDate.month, day)
                        val isWeekend = dayOfWeek == 0 || dayOfWeek == 6

                        // Range selection logic
                        val isInRange = when {
                            startDate == null || endDate == null -> false
                            else -> !date.isBefore(startDate) && !date.isAfter(endDate)
                        }
                        val isStart = date == startDate
                        val isEnd = date == endDate
                        val isSelected = isStart || isEnd

                        // Shape for start/end of range
                        val shape = when {
                            isStart -> RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                            isEnd -> RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                            else -> RoundedCornerShape(0.dp)
                        }

                        // Background colors
                        val backgroundColor = when {
                            isSelected -> MaterialTheme.colorScheme.primary
                            isInRange -> MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            else -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .clip(shape)
                                .background(backgroundColor)
                                .clickable { onDateSelected(day) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = when {
                                    isSelected -> MaterialTheme.colorScheme.onPrimary
                                    isWeekend -> Color(0xFFEF4444)
                                    else -> MaterialTheme.colorScheme.onSurface
                                }
                            )
                        }

                        currentDay++
                    } else {
                        Spacer(modifier = Modifier.weight(1f).height(48.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectedRangeText(startDate: LocalDate?, endDate: LocalDate?) {
    val text = when {
        startDate == null && endDate == null -> "No dates selected"
        endDate == null -> "Start: ${formatDate(startDate)}"
        else -> "Range: ${formatDate(startDate)} to ${formatDate(endDate)}"
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

private fun formatDate(date: LocalDate?): String {
    return date?.let {
        "${it.month.value}/${it.dayOfMonth}/${it.year}"
    } ?: ""
}

@Preview(showBackground = true)
@Composable
fun DateRangePickerPreview() {
    MaterialTheme {
        DateRangePicker(onDateChange = { date, date1 -> })
    }
}