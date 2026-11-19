package com.example

import com.example.model.ExerciseCategory
import com.example.model.ExerciseRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun verifyExerciseCatalogIntegrity() {
        val all = ExerciseRepository.allExercises
        assertEquals("Catalog should contain exactly 19 exercises", 19, all.size)

        // Verify Beginner
        val beginner = ExerciseRepository.getExercisesByCategory(ExerciseCategory.BEGINNER)
        assertEquals(4, beginner.size)
        assertTrue(beginner.any { it.id == "walking" })
        assertTrue(beginner.any { it.id == "jogging" })
        assertTrue(beginner.any { it.id == "jumping_jacks" })
        assertTrue(beginner.any { it.id == "marching_in_place" })

        // Verify Upper Body
        val upper = ExerciseRepository.getExercisesByCategory(ExerciseCategory.UPPER_BODY)
        assertEquals(4, upper.size)
        assertTrue(upper.any { it.id == "pushups" })
        assertTrue(upper.any { it.id == "wall_pushups" })
        assertTrue(upper.any { it.id == "shoulder_press" })
        assertTrue(upper.any { it.id == "arm_circles" })

        // Verify Lower Body
        val lower = ExerciseRepository.getExercisesByCategory(ExerciseCategory.LOWER_BODY)
        assertEquals(4, lower.size)
        assertTrue(lower.any { it.id == "squats" })
        assertTrue(lower.any { it.id == "lunges" })
        assertTrue(lower.any { it.id == "calf_raises" })
        assertTrue(lower.any { it.id == "glute_bridges" })

        // Verify Core
        val core = ExerciseRepository.getExercisesByCategory(ExerciseCategory.CORE)
        assertEquals(3, core.size)
        assertTrue(core.any { it.id == "plank" })
        assertTrue(core.any { it.id == "crunches" })
        assertTrue(core.any { it.id == "bicycle_crunches" })

        // Verify Stretching
        val stretch = ExerciseRepository.getExercisesByCategory(ExerciseCategory.STRETCHING)
        assertEquals(4, stretch.size)
        assertTrue(stretch.any { it.id == "neck_stretch" })
        assertTrue(stretch.any { it.id == "shoulder_stretch" })
        assertTrue(stretch.any { it.id == "hamstring_stretch" })
        assertTrue(stretch.any { it.id == "standing_side_stretch" })

        // Verify every single exercise has steps, posture tips, safety tips, and mistakes
        all.forEach { ex ->
            assertTrue("${ex.id} steps must not be empty", ex.steps.isNotEmpty())
            assertTrue("${ex.id} posture tips must not be empty", ex.correctPostureTips.isNotEmpty())
            assertTrue("${ex.id} safety tips must not be empty", ex.safetyTips.isNotEmpty())
            assertTrue("${ex.id} common mistakes must not be empty", ex.commonMistakes.isNotEmpty())
            assertTrue("${ex.id} calories must be positive", ex.caloriesPerMinute > 0)
        }
    }
}
