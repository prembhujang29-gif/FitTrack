package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.DailyProgressEntity
import com.example.data.local.FitTrackDatabase
import com.example.data.local.UserProfileEntity
import com.example.data.local.WorkoutEntity
import com.example.data.repository.FitnessRepository
import com.example.model.Exercise
import com.example.model.ExerciseCategory
import com.example.model.ExerciseRepository
import com.example.ui.components.DayProgressData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class FitnessViewModel(application: Application) : AndroidViewModel(application) {

    private val database = FitTrackDatabase.getDatabase(application)
    private val repository = FitnessRepository(database.dao())

    val todayEpochDay = LocalDate.now().toEpochDay()

    val allWorkouts: StateFlow<List<WorkoutEntity>> = repository.allWorkouts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val todaysWorkouts: StateFlow<List<WorkoutEntity>> = repository.getWorkoutsForDay(todayEpochDay)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val dailyProgress: StateFlow<DailyProgressEntity?> = repository.getDailyProgress(todayEpochDay)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val userProfile: StateFlow<UserProfileEntity> = repository.userProfile
        .map { it ?: UserProfileEntity() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserProfileEntity()
        )

    // Weekly progress data (last 7 days including today)
    val weeklyChartData: StateFlow<List<DayProgressData>> = combine(
        repository.getDailyProgressRange(todayEpochDay - 6, todayEpochDay),
        allWorkouts
    ) { progressList, _ ->
        val progressMap = progressList.associateBy { it.dateEpochDay }
        (0..6).map { daysAgo ->
            val epoch = todayEpochDay - (6 - daysAgo)
            val date = LocalDate.ofEpochDay(epoch)
            val dayName = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            val entry = progressMap[epoch]

            DayProgressData(
                dayName = dayName,
                epochDay = epoch,
                calories = entry?.caloriesBurned ?: 0,
                activeMinutes = entry?.activeMinutes ?: 0,
                steps = entry?.steps ?: 0,
                isToday = epoch == todayEpochDay
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _selectedExercise = MutableStateFlow<Exercise?>(null)
    val selectedExercise: StateFlow<Exercise?> = _selectedExercise.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<ExerciseCategory?>(null)
    val selectedCategoryFilter: StateFlow<ExerciseCategory?> = _selectedCategoryFilter.asStateFlow()

    private val _darkModeOverride = MutableStateFlow<Boolean?>(null)
    val darkModeOverride: StateFlow<Boolean?> = _darkModeOverride.asStateFlow()

    init {
        // Seed initial defaults only on first launch without overwriting saved user data
        viewModelScope.launch {
            repository.ensureInitialSeedData()
        }
    }

    fun selectExercise(exercise: Exercise?) {
        _selectedExercise.value = exercise
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setCategoryFilter(category: ExerciseCategory?) {
        _selectedCategoryFilter.value = category
    }

    fun addSteps(amount: Int) {
        viewModelScope.launch {
            repository.addSteps(amount)
        }
    }

    fun logWorkout(
        exerciseId: String,
        exerciseTitle: String,
        category: String,
        durationSeconds: Int,
        caloriesBurned: Int,
        notes: String = "",
        intensity: String = "Moderate"
    ) {
        viewModelScope.launch {
            repository.logWorkout(
                exerciseId = exerciseId,
                exerciseTitle = exerciseTitle,
                category = category,
                durationSeconds = durationSeconds,
                caloriesBurned = caloriesBurned,
                notes = notes,
                intensity = intensity
            )
        }
    }

    fun deleteWorkout(id: Long) {
        viewModelScope.launch {
            repository.deleteWorkout(id)
        }
    }

    fun updateProfile(profile: UserProfileEntity) {
        viewModelScope.launch {
            repository.saveProfile(profile)
            _darkModeOverride.value = profile.isDarkMode
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _darkModeOverride.value = enabled
        viewModelScope.launch {
            val updated = userProfile.value.copy(isDarkMode = enabled)
            repository.saveProfile(updated)
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            repository.clearAllData()
        }
    }
}
