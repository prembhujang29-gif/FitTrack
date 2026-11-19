package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CardioGreen
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.FlameOrange
import java.time.LocalDate

enum class ChartMetricType(val label: String, val unit: String, val color: Color) {
    CALORIES("Calories", "kcal", FlameOrange),
    DURATION("Active Mins", "mins", CardioGreen),
    STEPS("Steps", "steps", ElectricBlue)
}

data class DayProgressData(
    val dayName: String, // "Mon", "Tue", etc.
    val epochDay: Long,
    val calories: Int,
    val activeMinutes: Int,
    val steps: Int,
    val isToday: Boolean
)

@Composable
fun WeeklyProgressChart(
    weeklyData: List<DayProgressData>,
    modifier: Modifier = Modifier
) {
    var selectedMetric by remember { mutableStateOf(ChartMetricType.CALORIES) }

    val maxValue = remember(weeklyData, selectedMetric) {
        val max = weeklyData.maxOfOrNull {
            when (selectedMetric) {
                ChartMetricType.CALORIES -> it.calories
                ChartMetricType.DURATION -> it.activeMinutes
                ChartMetricType.STEPS -> it.steps
            }
        } ?: 100
        max.coerceAtLeast(1)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header Row with Metric Pills Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Weekly Activity",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Text(
                        text = "Last 7 Days Progress",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }

                // Toggle tabs
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(2.dp)
                ) {
                    ChartMetricType.values().forEach { metric ->
                        val isSelected = metric == selectedMetric
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isSelected) metric.color else Color.Transparent)
                                .clickable { selectedMetric = metric }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = metric.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bar Chart Area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .testTag("weekly_progress_chart"),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                weeklyData.forEach { dayData ->
                    val value = when (selectedMetric) {
                        ChartMetricType.CALORIES -> dayData.calories
                        ChartMetricType.DURATION -> dayData.activeMinutes
                        ChartMetricType.STEPS -> dayData.steps
                    }
                    val heightRatio = (value.toFloat() / maxValue).coerceIn(0.06f, 1f)

                    val animatedHeight by animateFloatAsState(
                        targetValue = heightRatio,
                        animationSpec = tween(durationMillis = 600),
                        label = "bar_height"
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        // Value label above bar
                        Text(
                            text = if (value >= 1000) "${value / 1000}k" else "$value",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = if (dayData.isToday) FontWeight.Bold else FontWeight.Normal,
                                color = if (dayData.isToday) selectedMetric.color else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Bar
                        Box(
                            modifier = Modifier
                                .width(22.dp)
                                .height((110 * animatedHeight).dp)
                                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                                .background(
                                    if (dayData.isToday) selectedMetric.color
                                    else selectedMetric.color.copy(alpha = 0.35f)
                                )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Day name label
                        Text(
                            text = dayData.dayName,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = if (dayData.isToday) FontWeight.Bold else FontWeight.Medium,
                                color = if (dayData.isToday) selectedMetric.color else MaterialTheme.colorScheme.onSurface
                            )
                        )

                        if (dayData.isToday) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .width(12.dp)
                                    .height(3.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(selectedMetric.color)
                            )
                        }
                    }
                }
            }
        }
    }
}
