package com.example.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.ExerciseCategory
import com.example.ui.screens.AddActivityScreen
import com.example.ui.screens.ExerciseDetailScreen
import com.example.ui.screens.ExerciseLibraryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ProgressScreen
import kotlinx.coroutines.launch

enum class NavTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val tag: String
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home, "nav_home"),
    EXERCISES("Exercises", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter, "nav_exercises"),
    ADD_ACTIVITY("Add", Icons.Filled.AddCircle, Icons.Filled.AddCircle, "nav_add_activity"),
    PROGRESS("Progress", Icons.Filled.BarChart, Icons.Outlined.BarChart, "nav_progress"),
    PROFILE("Profile", Icons.Filled.Person, Icons.Outlined.Person, "nav_profile")
}

@Composable
fun MainScreen(
    viewModel: FitnessViewModel,
    modifier: Modifier = Modifier
) {
    var currentTab by remember { mutableStateOf(NavTab.HOME) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val dailyProgress by viewModel.dailyProgress.collectAsStateWithLifecycle()
    val weeklyData by viewModel.weeklyChartData.collectAsStateWithLifecycle()
    val todaysWorkouts by viewModel.todaysWorkouts.collectAsStateWithLifecycle()
    val allWorkouts by viewModel.allWorkouts.collectAsStateWithLifecycle()
    val selectedExercise by viewModel.selectedExercise.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategoryFilter by viewModel.selectedCategoryFilter.collectAsStateWithLifecycle()

    // If an exercise is selected, show the full ExerciseDetailScreen
    if (selectedExercise != null) {
        val exercise = selectedExercise!!
        ExerciseDetailScreen(
            exercise = exercise,
            onBack = {
                viewModel.selectExercise(null)
            },
            onWorkoutCompleted = { durationSeconds, caloriesBurned ->
                viewModel.logWorkout(
                    exerciseId = exercise.id,
                    exerciseTitle = exercise.title,
                    category = exercise.category.title,
                    durationSeconds = durationSeconds,
                    caloriesBurned = caloriesBurned,
                    notes = "Completed ${exercise.title} session",
                    intensity = "Moderate"
                )
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Workout recorded: +$caloriesBurned kcal!")
                }
            }
        )
        return
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar(
                windowInsets = WindowInsets.navigationBars,
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                NavTab.values().forEach { tab ->
                    val isSelected = currentTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentTab = tab },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.title
                            )
                        },
                        label = {
                            Text(
                                text = tab.title,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.testTag(tab.tag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "nav_tab_transition"
            ) { targetTab ->
                when (targetTab) {
                    NavTab.HOME -> {
                        HomeScreen(
                            userProfile = userProfile,
                            dailyProgress = dailyProgress,
                            weeklyData = weeklyData,
                            todaysWorkouts = todaysWorkouts,
                            onNavigateToExercises = { cat ->
                                viewModel.setCategoryFilter(cat)
                                currentTab = NavTab.EXERCISES
                            },
                            onSelectExercise = { ex ->
                                viewModel.selectExercise(ex)
                            },
                            onNavigateToAddActivity = {
                                currentTab = NavTab.ADD_ACTIVITY
                            },
                            onQuickAddSteps = { steps ->
                                viewModel.addSteps(steps)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Added $steps steps!")
                                }
                            }
                        )
                    }

                    NavTab.EXERCISES -> {
                        ExerciseLibraryScreen(
                            searchQuery = searchQuery,
                            onSearchQueryChange = { viewModel.setSearchQuery(it) },
                            selectedCategory = selectedCategoryFilter,
                            onCategorySelect = { viewModel.setCategoryFilter(it) },
                            onSelectExercise = { ex ->
                                viewModel.selectExercise(ex)
                            }
                        )
                    }

                    NavTab.ADD_ACTIVITY -> {
                        AddActivityScreen(
                            onSaveActivity = { name, cat, dur, cal, notes, intensity ->
                                viewModel.logWorkout(
                                    exerciseId = "custom_${System.currentTimeMillis()}",
                                    exerciseTitle = name,
                                    category = cat,
                                    durationSeconds = dur * 60,
                                    caloriesBurned = cal,
                                    notes = notes,
                                    intensity = intensity
                                )
                                currentTab = NavTab.HOME
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Logged $name successfully!")
                                }
                            },
                            onCancel = {
                                currentTab = NavTab.HOME
                            }
                        )
                    }

                    NavTab.PROGRESS -> {
                        ProgressScreen(
                            userProfile = userProfile,
                            dailyProgress = dailyProgress,
                            weeklyData = weeklyData,
                            workoutHistory = allWorkouts,
                            onDeleteWorkout = { id ->
                                viewModel.deleteWorkout(id)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Workout record removed")
                                }
                            },
                            onQuickAddSteps = { steps ->
                                viewModel.addSteps(steps)
                            }
                        )
                    }

                    NavTab.PROFILE -> {
                        val isDark = viewModel.darkModeOverride.collectAsStateWithLifecycle().value
                            ?: userProfile.isDarkMode
                        ProfileScreen(
                            userProfile = userProfile,
                            isDarkMode = isDark,
                            onToggleDarkMode = { viewModel.toggleDarkMode(it) },
                            onSaveProfile = { profile ->
                                viewModel.updateProfile(profile)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Profile updated!")
                                }
                            },
                            onClearAllData = {
                                viewModel.clearAllData()
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Workout records cleared")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
