package com.example.model

enum class ExerciseCategory(val title: String, val badgeColorHex: Long) {
    BEGINNER("Beginner", 0xFF10B981),
    UPPER_BODY("Upper Body", 0xFF3B82F6),
    LOWER_BODY("Lower Body", 0xFF8B5CF6),
    CORE("Core", 0xFFF97316),
    STRETCHING("Stretching", 0xFF0D9488)
}

data class ExerciseStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val focusCue: String,
    val poseKey: String // Used by the animated pose demonstrator
)

data class Exercise(
    val id: String,
    val title: String,
    val category: ExerciseCategory,
    val difficulty: String, // "Beginner", "Intermediate", "All Levels"
    val targetMuscles: List<String>,
    val equipment: String,
    val caloriesPerMinute: Int,
    val defaultDurationSeconds: Int = 60,
    val overview: String,
    val steps: List<ExerciseStep>,
    val correctPostureTips: List<String>,
    val safetyTips: List<String>,
    val commonMistakes: List<String>,
    val drawableResName: String? = null // e.g., "img_pushups", "img_squats", etc.
)
