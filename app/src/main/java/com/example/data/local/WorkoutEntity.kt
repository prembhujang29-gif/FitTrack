package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_records")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val exerciseId: String,
    val exerciseTitle: String,
    val category: String,
    val durationSeconds: Int,
    val caloriesBurned: Int,
    val dateEpochDay: Long,
    val timestamp: Long = System.currentTimeMillis(),
    val notes: String = "",
    val intensity: String = "Moderate"
)
