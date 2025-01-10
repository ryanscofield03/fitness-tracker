package nz.ac.canterbury.seng303.healthtracking.viewmodels.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.healthtracking.daos.ExerciseDao
import nz.ac.canterbury.seng303.healthtracking.entities.Exercise
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ExerciseViewModel(
    private val exerciseDao: ExerciseDao,
) : ViewModel() {
    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()

    fun addExercise(exercise: Exercise, onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val exerciseId = exerciseDao.upsertExercise(exercise)
            onResult(exerciseId)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.deleteExercise(exercise)
        }
    }

    fun editExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.upsertExercise(exercise)
        }
    }

    suspend fun addExerciseSuspendSuspendCoroutineWrapper(exercise: Exercise): Long =
        suspendCoroutine { continuation ->
            addExercise(exercise) { exerciseId ->
                continuation.resume(exerciseId)
            }
        }

    suspend fun deleteExerciseSuspendSuspendCoroutineWrapper(exercise: Exercise) =
        suspendCoroutine { continuation ->
            deleteExercise(exercise)
            continuation.resume(Unit)
        }
}