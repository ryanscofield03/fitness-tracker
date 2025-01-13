package nz.ac.canterbury.seng303.healthtracking.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import io.reactivex.Flowable
import nz.ac.canterbury.seng303.healthtracking.entities.Exercise
import nz.ac.canterbury.seng303.healthtracking.entities.Workout
import nz.ac.canterbury.seng303.healthtracking.entities.WorkoutExerciseCrossRef
import nz.ac.canterbury.seng303.healthtracking.entities.WorkoutHistory
import nz.ac.canterbury.seng303.healthtracking.entities.WorkoutHistoryCrossRef

/**
 * DAO for workouts, this allows CRUD operations on workouts in DB
 */
@Dao
interface WorkoutDao {
    @Upsert
    suspend fun upsertWorkout(workout: Workout): Long

    @Transaction
    suspend fun deleteWorkoutAndAssociatedData(workout: Workout) {
        deleteWorkoutExerciseCrossRefs(workout.id)
        deleteWorkoutHistoryCrossRefs(workout.id)
        deleteAssociatedExercises(workout.id)
        deleteAssociatedHistory(workout.id)
        deleteWorkout(workout.id)
    }

    @Query("DELETE FROM Workout WHERE id = :workoutId")
    suspend fun deleteWorkout(workoutId: Long)

    @Query("DELETE FROM WorkoutExerciseCrossRef WHERE workoutId = :workoutId")
    suspend fun deleteWorkoutExerciseCrossRefs(workoutId: Long)

    @Query("DELETE FROM WorkoutHistoryCrossRef WHERE workoutId = :workoutId")
    suspend fun deleteWorkoutHistoryCrossRefs(workoutId: Long)

    @Query("""
        DELETE FROM Exercise 
        WHERE id IN (
            SELECT e.id
            FROM Exercise e
            INNER JOIN WorkoutExerciseCrossRef wecr ON e.id = wecr.exerciseId
            WHERE wecr.workoutId = :workoutId
        )
    """)
    suspend fun deleteAssociatedExercises(workoutId: Long)

    @Query("""
        DELETE FROM Workout_History 
        WHERE id IN (
            SELECT wh.id
            FROM Workout_History wh
            INNER JOIN WorkoutHistoryCrossRef whcr ON wh.id = whcr.workoutHistoryId
            WHERE whcr.workoutId = :workoutId
        )
    """)
    suspend fun deleteAssociatedHistory(workoutId: Long)

    @Query("""
        DELETE 
        FROM WorkoutExerciseCrossRef 
        WHERE workoutId = :workoutId AND exerciseId = :exerciseId
        """)
    suspend fun deleteWorkoutExerciseCrossRef(workoutId: Long, exerciseId: Long)

    @Query("SELECT * FROM workout")
    fun getAllWorkouts(): LiveData<List<Workout>>

    @Query("""
        SELECT e.* 
        FROM Exercise e
        INNER JOIN WorkoutExerciseCrossRef wecr ON e.id = wecr.exerciseId
        WHERE wecr.workoutId = :workoutId
    """)
    fun getExercisesForWorkout(workoutId: Long): List<Exercise>

    @Query("""
        SELECT wh.* 
        FROM Workout_History wh
        INNER JOIN WorkoutHistoryCrossRef whcr ON wh.id = whcr.workoutHistoryId
        WHERE whcr.workoutId = :workoutId
    """)
    fun getHistoryForWorkout(workoutId: Long): List<WorkoutHistory>
}