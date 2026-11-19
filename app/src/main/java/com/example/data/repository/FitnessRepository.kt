package com.example.data.repository

import com.example.data.local.DailyProgressEntity
import com.example.data.local.FitTrackDao
import com.example.data.local.UserProfileEntity
import com.example.data.local.WorkoutEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class FitnessRepository(private val dao: FitTrackDao) {

    val allWorkouts: Flow<List<WorkoutEntity>> = dao.getAllWorkouts()
    val userProfile: Flow<UserProfileEntity?> = dao.getUserProfile()

    fun getWorkoutsForDay(epochDay: Long): Flow<List<WorkoutEntity>> =
        dao.getWorkoutsForDay(epochDay)

    fun getDailyProgress(epochDay: Long): Flow<DailyProgressEntity?> =
        dao.getDailyProgress(epochDay)

    fun getDailyProgressRange(startEpochDay: Long, endEpochDay: Long): Flow<List<DailyProgressEntity>> =
        dao.getDailyProgressRange(startEpochDay, endEpochDay)

    suspend fun logWorkout(
        exerciseId: String,
        exerciseTitle: String,
        category: String,
        durationSeconds: Int,
        caloriesBurned: Int,
        notes: String = "",
        intensity: String = "Moderate"
    ): Long {
        val todayEpochDay = LocalDate.now().toEpochDay()
        val workout = WorkoutEntity(
            exerciseId = exerciseId,
            exerciseTitle = exerciseTitle,
            category = category,
            durationSeconds = durationSeconds,
            caloriesBurned = caloriesBurned,
            dateEpochDay = todayEpochDay,
            timestamp = System.currentTimeMillis(),
            notes = notes,
            intensity = intensity
        )
        val id = dao.insertWorkout(workout)

        // Automatically update today's daily progress using profile goals
        val currentProgress = dao.getDailyProgressSync(todayEpochDay)
        val profile = dao.getUserProfileSync() ?: UserProfileEntity()
        val activeMins = (durationSeconds + 59) / 60
        if (currentProgress != null) {
            val updated = currentProgress.copy(
                activeMinutes = currentProgress.activeMinutes + activeMins,
                caloriesBurned = currentProgress.caloriesBurned + caloriesBurned
            )
            dao.insertOrUpdateDailyProgress(updated)
        } else {
            val newProgress = DailyProgressEntity(
                dateEpochDay = todayEpochDay,
                steps = 0,
                stepsGoal = profile.dailyStepGoal,
                activeMinutes = activeMins,
                activeMinutesGoal = profile.dailyActiveMinutesGoal,
                caloriesBurned = caloriesBurned,
                caloriesGoal = profile.dailyCalorieGoal
            )
            dao.insertOrUpdateDailyProgress(newProgress)
        }
        return id
    }

    suspend fun ensureInitialSeedData() {
        if (dao.getUserProfileSync() == null) {
            dao.saveUserProfile(UserProfileEntity())
        }
        val todayEpochDay = LocalDate.now().toEpochDay()
        if (dao.getDailyProgressSync(todayEpochDay) == null) {
            val profile = dao.getUserProfileSync() ?: UserProfileEntity()
            dao.insertOrUpdateDailyProgress(
                DailyProgressEntity(
                    dateEpochDay = todayEpochDay,
                    steps = 0,
                    stepsGoal = profile.dailyStepGoal,
                    activeMinutes = 0,
                    activeMinutesGoal = profile.dailyActiveMinutesGoal,
                    caloriesBurned = 0,
                    caloriesGoal = profile.dailyCalorieGoal
                )
            )
        }
    }

    suspend fun addSteps(additionalSteps: Int) {
        val todayEpochDay = LocalDate.now().toEpochDay()
        val profile = dao.getUserProfileSync() ?: UserProfileEntity()
        val current = dao.getDailyProgressSync(todayEpochDay) ?: DailyProgressEntity(
            dateEpochDay = todayEpochDay,
            steps = 0,
            stepsGoal = profile.dailyStepGoal,
            activeMinutes = 0,
            activeMinutesGoal = profile.dailyActiveMinutesGoal,
            caloriesBurned = 0,
            caloriesGoal = profile.dailyCalorieGoal
        )
        // ~25 steps ≈ 1 calorie
        val estCalories = additionalSteps / 25
        val updated = current.copy(
            steps = current.steps + additionalSteps,
            caloriesBurned = current.caloriesBurned + estCalories
        )
        dao.insertOrUpdateDailyProgress(updated)
    }

    suspend fun updateDailyProgress(progress: DailyProgressEntity) {
        dao.insertOrUpdateDailyProgress(progress)
    }

    suspend fun deleteWorkout(id: Long) {
        dao.deleteWorkout(id)
    }

    suspend fun saveProfile(profile: UserProfileEntity) {
        dao.saveUserProfile(profile)
        val todayEpochDay = LocalDate.now().toEpochDay()
        val current = dao.getDailyProgressSync(todayEpochDay)
        if (current != null) {
            dao.insertOrUpdateDailyProgress(
                current.copy(
                    stepsGoal = profile.dailyStepGoal,
                    activeMinutesGoal = profile.dailyActiveMinutesGoal,
                    caloriesGoal = profile.dailyCalorieGoal
                )
            )
        }
    }

    suspend fun clearAllData() {
        dao.clearAllWorkouts()
        dao.clearAllDailyProgress()
        val todayEpochDay = LocalDate.now().toEpochDay()
        val profile = dao.getUserProfileSync() ?: UserProfileEntity()
        dao.insertOrUpdateDailyProgress(
            DailyProgressEntity(
                dateEpochDay = todayEpochDay,
                steps = 0,
                stepsGoal = profile.dailyStepGoal,
                activeMinutes = 0,
                activeMinutesGoal = profile.dailyActiveMinutesGoal,
                caloriesBurned = 0,
                caloriesGoal = profile.dailyCalorieGoal
            )
        )
    }
}
