package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CardioGreen
import com.example.ui.theme.FlameOrange
import kotlinx.coroutines.delay

@Composable
fun WorkoutTimerComponent(
    defaultDurationSeconds: Int = 60,
    caloriesPerMinute: Int = 8,
    onWorkoutCompleted: (durationSeconds: Int, caloriesBurned: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var timerModeIsCountdown by remember { mutableStateOf(true) }
    var targetSeconds by remember { mutableIntStateOf(defaultDurationSeconds) }
    var remainingSeconds by remember { mutableIntStateOf(defaultDurationSeconds) }
    var elapsedSeconds by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var hasCompletedSession by remember { mutableStateOf(false) }

    // Live timer ticking
    LaunchedEffect(isRunning, remainingSeconds, elapsedSeconds) {
        if (isRunning) {
            delay(1000)
            elapsedSeconds++
            if (timerModeIsCountdown) {
                if (remainingSeconds > 0) {
                    remainingSeconds--
                } else {
                    isRunning = false
                }
            }
        }
    }

    val displaySeconds = if (timerModeIsCountdown) remainingSeconds else elapsedSeconds
    val mins = displaySeconds / 60
    val secs = displaySeconds % 60
    val formattedTime = String.format("%02d:%02d", mins, secs)

    // Calculate calories burned dynamically
    val currentCalories = ((elapsedSeconds / 60.0) * caloriesPerMinute).toInt().coerceAtLeast(1)

    val progress by animateFloatAsState(
        targetValue = if (targetSeconds > 0 && timerModeIsCountdown) {
            (1f - (remainingSeconds.toFloat() / targetSeconds)).coerceIn(0f, 1f)
        } else {
            ((elapsedSeconds % 60) / 60f)
        },
        label = "timer_progress"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Row: Title & Mode Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Workout Timer",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (timerModeIsCountdown) MaterialTheme.colorScheme.primary
                                else Color.Transparent
                            )
                            .clickable {
                                timerModeIsCountdown = true
                                isRunning = false
                            }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("timer_mode_countdown")
                    ) {
                        Text(
                            text = "Countdown",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (timerModeIsCountdown) MaterialTheme.colorScheme.onPrimary
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (!timerModeIsCountdown) MaterialTheme.colorScheme.primary
                                else Color.Transparent
                            )
                            .clickable {
                                timerModeIsCountdown = false
                                isRunning = false
                            }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("timer_mode_stopwatch")
                    ) {
                        Text(
                            text = "Stopwatch",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (!timerModeIsCountdown) MaterialTheme.colorScheme.onPrimary
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Circular Visual Timer
            val ringTrackColor = MaterialTheme.colorScheme.surfaceVariant
            val ringActiveColor = if (progress >= 1f && timerModeIsCountdown) CardioGreen else MaterialTheme.colorScheme.primary

            Box(
                modifier = Modifier.size(190.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(180.dp)) {
                    val stroke = 12.dp.toPx()
                    // Track
                    drawCircle(
                        color = ringTrackColor,
                        style = Stroke(width = stroke)
                    )
                    // Progress Arc
                    drawArc(
                        color = ringActiveColor,
                        startAngle = -90f,
                        sweepAngle = progress * 360f,
                        useCenter = false,
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            letterSpacing = 1.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "🔥 ~$currentCalories kcal",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = FlameOrange
                        )
                    )

                    Text(
                        text = if (isRunning) "ACTIVE" else if (remainingSeconds == 0) "TIME'S UP!" else "PAUSED",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            color = if (isRunning) CardioGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quick add time buttons (+15s, +30s)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = {
                        targetSeconds += 15
                        remainingSeconds += 15
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("+15s")
                }

                FilledTonalButton(
                    onClick = {
                        targetSeconds += 30
                        remainingSeconds += 30
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("+30s")
                }

                OutlinedButton(
                    onClick = {
                        isRunning = false
                        targetSeconds = defaultDurationSeconds
                        remainingSeconds = defaultDurationSeconds
                        elapsedSeconds = 0
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("timer_reset_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reset timer",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reset")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Primary Timer Action: Start / Pause
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { isRunning = !isRunning },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("timer_start_pause_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRunning) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isRunning) "Pause" else "Start Workout"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isRunning) "Pause Timer" else "Start Workout",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Mark as Completed Button
            Button(
                onClick = {
                    val finalDuration = if (elapsedSeconds > 0) elapsedSeconds else targetSeconds
                    val finalCalories = ((finalDuration / 60.0) * caloriesPerMinute).toInt().coerceAtLeast(2)
                    hasCompletedSession = true
                    isRunning = false
                    onWorkoutCompleted(finalDuration, finalCalories)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("mark_completed_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CardioGreen,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Complete workout"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Mark as Completed",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
            }

            AnimatedVisibility(visible = hasCompletedSession) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(CardioGreen.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🎉 Awesome work! Session saved to your daily progress.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = CardioGreen
                        )
                    )
                }
            }
        }
    }
}
