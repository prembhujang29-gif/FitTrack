package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ExerciseStep
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ExercisePoseDemonstrator(
    steps: List<ExerciseStep>,
    exerciseId: String,
    modifier: Modifier = Modifier
) {
    var currentStepIndex by remember(exerciseId) { mutableIntStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }
    val currentStep = steps.getOrNull(currentStepIndex) ?: return

    // Auto-advance demonstration when playing
    LaunchedEffect(isPlaying, currentStepIndex) {
        if (isPlaying) {
            delay(2800)
            currentStepIndex = (currentStepIndex + 1) % steps.size
        }
    }

    // Gentle breathing pulse animation for the visual pose
    val pulseAnim = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        pulseAnim.animateTo(
            targetValue = 1.06f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header bar: Step count & Play/Pause/Replay Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "STEP ${currentStep.stepNumber} OF ${steps.size}",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            isPlaying = !isPlaying
                        },
                        modifier = Modifier.testTag("demo_play_pause_button")
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause demonstration" else "Play demonstration",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = {
                            currentStepIndex = 0
                            isPlaying = true
                        },
                        modifier = Modifier.testTag("demo_replay_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Replay demonstration from start",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Canvas Pose Demonstrator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                    )
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                        RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                val primaryColor = MaterialTheme.colorScheme.primary
                val accentColor = MaterialTheme.colorScheme.tertiary
                val floorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)

                Canvas(modifier = Modifier.fillMaxWidth().height(180.dp)) {
                    val w = size.width
                    val h = size.height
                    val cx = w / 2f
                    val cy = h / 2f

                    // Exercise Mat / Ground Line
                    drawLine(
                        color = floorColor,
                        start = Offset(w * 0.15f, h * 0.85f),
                        end = Offset(w * 0.85f, h * 0.85f),
                        strokeWidth = 4f,
                        cap = StrokeCap.Round
                    )

                    // Draw stylized dynamic pose based on exercise poseKey & stepNumber
                    val pose = currentStep.poseKey
                    drawExercisePose(
                        pose = pose,
                        stepNumber = currentStep.stepNumber,
                        cx = cx,
                        cy = cy,
                        primaryColor = primaryColor,
                        accentColor = accentColor,
                        pulseScale = pulseAnim.value
                    )
                }

                // Step Focus Cue Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "💡 ${currentStep.focusCue}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Step Title & Instruction
            Text(
                text = currentStep.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = currentStep.instruction,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Buttons: Previous Step & Next Step
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = {
                        if (currentStepIndex > 0) currentStepIndex--
                    },
                    enabled = currentStepIndex > 0,
                    modifier = Modifier.testTag("demo_prev_step_button"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Previous step"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Previous Step")
                }

                // Step Dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    steps.indices.forEach { index ->
                        Box(
                            modifier = Modifier
                                .size(if (index == currentStepIndex) 10.dp else 6.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == currentStepIndex)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                                )
                        )
                    }
                }

                FilledTonalButton(
                    onClick = {
                        if (currentStepIndex < steps.size - 1) {
                            currentStepIndex++
                        } else {
                            currentStepIndex = 0
                        }
                    },
                    modifier = Modifier.testTag("demo_next_step_button"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (currentStepIndex < steps.size - 1) "Next Step" else "Start Over")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Next step"
                    )
                }
            }
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawExercisePose(
    pose: String,
    stepNumber: Int,
    cx: Float,
    cy: Float,
    primaryColor: Color,
    accentColor: Color,
    pulseScale: Float
) {
    val limbStroke = 7f
    val torsoStroke = 9f
    val headRadius = 14f

    when {
        // Horizontal Planks & Pushups
        pose.startsWith("pushup") || pose.startsWith("plank") -> {
            val isLow = stepNumber == 2 || stepNumber == 3
            val yLevel = if (isLow) cy + 30f else cy + 10f

            // Head
            drawCircle(
                color = primaryColor,
                radius = headRadius,
                center = Offset(cx - 70f, yLevel - 15f)
            )
            // Torso & Legs line
            drawLine(
                color = primaryColor,
                start = Offset(cx - 55f, yLevel),
                end = Offset(cx + 65f, yLevel + 25f),
                strokeWidth = torsoStroke,
                cap = StrokeCap.Round
            )
            // Arms
            if (isLow) {
                // Bent elbow
                drawLine(
                    color = accentColor,
                    start = Offset(cx - 50f, yLevel),
                    end = Offset(cx - 30f, yLevel - 20f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = accentColor,
                    start = Offset(cx - 30f, yLevel - 20f),
                    end = Offset(cx - 45f, cy + 45f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
            } else {
                // Extended arm
                drawLine(
                    color = accentColor,
                    start = Offset(cx - 50f, yLevel),
                    end = Offset(cx - 45f, cy + 45f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
            }
            // Feet
            drawLine(
                color = primaryColor,
                start = Offset(cx + 65f, yLevel + 25f),
                end = Offset(cx + 70f, cy + 45f),
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
        }

        // Supine exercises (Bridges, Crunches, Bicycle crunches)
        pose.startsWith("bridge") || pose.startsWith("crunch") || pose.startsWith("bike") -> {
            val isBridgeUp = pose.startsWith("bridge") && (stepNumber == 3 || stepNumber == 4)
            val headY = cy + 25f
            val hipY = if (isBridgeUp) cy - 15f else cy + 25f

            // Head
            drawCircle(
                color = primaryColor,
                radius = headRadius,
                center = Offset(cx - 65f, headY)
            )
            // Torso
            drawLine(
                color = primaryColor,
                start = Offset(cx - 50f, headY),
                end = Offset(cx, hipY),
                strokeWidth = torsoStroke,
                cap = StrokeCap.Round
            )
            // Knees & Feet
            drawLine(
                color = accentColor,
                start = Offset(cx, hipY),
                end = Offset(cx + 35f, cy - 25f),
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
            drawLine(
                color = accentColor,
                start = Offset(cx + 35f, cy - 25f),
                end = Offset(cx + 45f, cy + 30f),
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
        }

        // Squats & Lunges
        pose.startsWith("squat") || pose.startsWith("lunge") -> {
            val isDeep = stepNumber == 2 || stepNumber == 3
            val hipDrop = if (isDeep) 35f else 0f

            // Head
            drawCircle(
                color = primaryColor,
                radius = headRadius,
                center = Offset(cx - 10f, cy - 50f + hipDrop)
            )
            // Torso
            drawLine(
                color = primaryColor,
                start = Offset(cx - 10f, cy - 35f + hipDrop),
                end = Offset(cx - 15f, cy + hipDrop),
                strokeWidth = torsoStroke,
                cap = StrokeCap.Round
            )
            // Arms forward
            drawLine(
                color = accentColor,
                start = Offset(cx - 10f, cy - 25f + hipDrop),
                end = Offset(cx + 30f, cy - 20f + hipDrop),
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
            // Thighs
            if (isDeep) {
                // Thigh parallel
                drawLine(
                    color = primaryColor,
                    start = Offset(cx - 15f, cy + hipDrop),
                    end = Offset(cx + 20f, cy + hipDrop),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                // Shin vertical
                drawLine(
                    color = primaryColor,
                    start = Offset(cx + 20f, cy + hipDrop),
                    end = Offset(cx + 20f, cy + 50f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
            } else {
                // Standing legs
                drawLine(
                    color = primaryColor,
                    start = Offset(cx - 15f, cy),
                    end = Offset(cx - 15f, cy + 50f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
            }
        }

        // Overhead & Jumping Jacks
        pose.startsWith("jack") || pose.startsWith("press") -> {
            val isOverhead = stepNumber == 2 || stepNumber == 3
            // Head
            drawCircle(
                color = primaryColor,
                radius = headRadius,
                center = Offset(cx, cy - 50f)
            )
            // Torso
            drawLine(
                color = primaryColor,
                start = Offset(cx, cy - 35f),
                end = Offset(cx, cy + 10f),
                strokeWidth = torsoStroke,
                cap = StrokeCap.Round
            )
            // Arms
            if (isOverhead) {
                drawLine(
                    color = accentColor,
                    start = Offset(cx, cy - 30f),
                    end = Offset(cx - 30f, cy - 65f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = accentColor,
                    start = Offset(cx, cy - 30f),
                    end = Offset(cx + 30f, cy - 65f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                // Spread legs
                drawLine(
                    color = primaryColor,
                    start = Offset(cx, cy + 10f),
                    end = Offset(cx - 30f, cy + 50f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = primaryColor,
                    start = Offset(cx, cy + 10f),
                    end = Offset(cx + 30f, cy + 50f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
            } else {
                // Arms down
                drawLine(
                    color = accentColor,
                    start = Offset(cx, cy - 30f),
                    end = Offset(cx - 25f, cy),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = accentColor,
                    start = Offset(cx, cy - 30f),
                    end = Offset(cx + 25f, cy),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                // Legs together
                drawLine(
                    color = primaryColor,
                    start = Offset(cx, cy + 10f),
                    end = Offset(cx - 10f, cy + 50f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = primaryColor,
                    start = Offset(cx, cy + 10f),
                    end = Offset(cx + 10f, cy + 50f),
                    strokeWidth = limbStroke,
                    cap = StrokeCap.Round
                )
            }
        }

        // Default Athletic Standing / Walking / Stretching Pose
        else -> {
            val tilt = if (pose.startsWith("side")) 20f else 0f
            // Head
            drawCircle(
                color = primaryColor,
                radius = headRadius,
                center = Offset(cx + tilt, cy - 50f)
            )
            // Torso
            drawLine(
                color = primaryColor,
                start = Offset(cx + tilt * 0.7f, cy - 35f),
                end = Offset(cx, cy + 10f),
                strokeWidth = torsoStroke,
                cap = StrokeCap.Round
            )
            // Arms
            val armEnd = if (pose.startsWith("side")) Offset(cx + 40f, cy - 65f) else Offset(cx - 25f, cy - 5f)
            drawLine(
                color = accentColor,
                start = Offset(cx + tilt * 0.7f, cy - 30f),
                end = armEnd,
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
            // Legs
            drawLine(
                color = primaryColor,
                start = Offset(cx, cy + 10f),
                end = Offset(cx - 15f, cy + 50f),
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
            drawLine(
                color = primaryColor,
                start = Offset(cx, cy + 10f),
                end = Offset(cx + 15f, cy + 50f),
                strokeWidth = limbStroke,
                cap = StrokeCap.Round
            )
        }
    }
}
