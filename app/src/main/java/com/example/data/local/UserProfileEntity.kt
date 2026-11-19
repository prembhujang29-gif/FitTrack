package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val name: String = "Alex Morgan",
    val avatarId: String = "avatar_1",
    val age: Int = 28,
    val weightKg: Float = 68.0f,
    val heightCm: Float = 175.0f,
    val gender: String = "Prefer not to say",
    val fitnessGoal: String = "Weight Loss & Muscle Tone",
    val activityPreference: String = "Mixed Cardio & Bodyweight",
    val dailyStepGoal: Int = 8000,
    val dailyCalorieGoal: Int = 500,
    val dailyActiveMinutesGoal: Int = 45,
    val isDarkMode: Boolean = false
)
