package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FitTrackDao {

    // Workout Records
    @Query("SELECT * FROM workout_records ORDER BY timestamp DESC")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workout_records WHERE dateEpochDay = :epochDay ORDER BY timestamp DESC")
    fun getWorkoutsForDay(epochDay: Long): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workout_records WHERE dateEpochDay >= :startEpochDay AND dateEpochDay <= :endEpochDay ORDER BY timestamp DESC")
    fun getWorkoutsBetweenDays(startEpochDay: Long, endEpochDay: Long): Flow<List<WorkoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Query("DELETE FROM workout_records WHERE id = :id")
    suspend fun deleteWorkout(id: Long)

    @Query("DELETE FROM workout_records")
    suspend fun clearAllWorkouts()

    // Daily Progress
    @Query("SELECT * FROM daily_progress WHERE dateEpochDay = :epochDay")
    fun getDailyProgress(epochDay: Long): Flow<DailyProgressEntity?>

    @Query("SELECT * FROM daily_progress WHERE dateEpochDay = :epochDay")
    suspend fun getDailyProgressSync(epochDay: Long): DailyProgressEntity?

    @Query("SELECT * FROM daily_progress WHERE dateEpochDay >= :startEpochDay AND dateEpochDay <= :endEpochDay ORDER BY dateEpochDay ASC")
    fun getDailyProgressRange(startEpochDay: Long, endEpochDay: Long): Flow<List<DailyProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateDailyProgress(progress: DailyProgressEntity)

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getUserProfileSync(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)

    @Query("DELETE FROM daily_progress")
    suspend fun clearAllDailyProgress()
}
