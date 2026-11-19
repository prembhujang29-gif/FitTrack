package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_progress")
data class DailyProgressEntity(
    @PrimaryKey
    val dateEpochDay: Long, // LocalDate.now().toEpochDay()
    val steps: Int = 0,
    val stepsGoal: Int = 8000,
    val activeMinutes: Int = 0,
    val activeMinutesGoal: Int = 45,
    val caloriesBurned: Int = 0,
    val caloriesGoal: Int = 500
)
