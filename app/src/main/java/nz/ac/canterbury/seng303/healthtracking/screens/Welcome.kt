package nz.ac.canterbury.seng303.healthtracking.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.healthtracking.R
import nz.ac.canterbury.seng303.healthtracking.ui.theme.primaryFont

@Composable
fun Welcome(modifier: Modifier = Modifier, navController: NavController) {
    val workoutString = stringResource(R.string.workout_screen)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF923E3E))
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { PointerInputChange, Offset -> },
                    onDragEnd = { navController.navigate(workoutString)}
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to GymHealth!",
            style = MaterialTheme.typography.displayMedium.copy(
                fontFamily = primaryFont,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 45.sp
            ),
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Your journey to fitness starts here.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = primaryFont,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Icon(
            modifier = Modifier
                .size(100.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right),
            contentDescription = "arrow_right"
        )
    }
}