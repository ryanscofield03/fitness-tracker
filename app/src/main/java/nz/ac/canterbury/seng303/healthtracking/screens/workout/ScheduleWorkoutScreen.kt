package nz.ac.canterbury.seng303.healthtracking.screens.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.healthtracking.R
import nz.ac.canterbury.seng303.healthtracking.viewmodels.screen.AddWorkoutViewModel
import java.time.DayOfWeek

val daysOfWeek = listOf(
    DayOfWeek.MONDAY to R.string.monday,
    DayOfWeek.TUESDAY to R.string.tuesday,
    DayOfWeek.WEDNESDAY to R.string.wednesday,
    DayOfWeek.THURSDAY to R.string.thursday,
    DayOfWeek.FRIDAY to R.string.friday,
    DayOfWeek.SATURDAY to R.string.saturday,
    DayOfWeek.SUNDAY to R.string.sunday
)

@Composable
fun ScheduleWorkout(
    modifier: Modifier,
    navController: NavController,
    viewModel: AddWorkoutViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedDays = rememberSaveable(
            saver = listSaver(
                save = { it.map { day -> day.value } },
                restore = { (mutableStateListOf<DayOfWeek>().apply { addAll(it.map { day -> DayOfWeek.of(day) }) })  }
            )
        ) { mutableStateListOf<DayOfWeek>() }

        Text(
            text = stringResource(id = R.string.schedule_workout),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(30.dp))

        daysOfWeek.forEach { (day, stringResId) ->
            ToggleDayButton(
                dayName = stringResource(id = stringResId),
                toggle = { viewModel.toggleScheduledDay(day) },
                isSelected = { viewModel.scheduledDays.contains(day) }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick =
            {
                navController.navigate("addWorkout")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Text(stringResource(id = R.string.save_schedule))
        }
    }
}

@Composable
fun ToggleDayButton(
    dayName: String,
    toggle: () -> Unit,
    isSelected: () -> Boolean
){
    Button(
        onClick = { toggle() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = if (isSelected()) 1f else 0.2f),
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = if (isSelected()) 1f else 0.7f)
        )) {
        Text(text = dayName)
    }
}