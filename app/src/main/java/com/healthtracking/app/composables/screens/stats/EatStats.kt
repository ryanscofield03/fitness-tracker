package com.healthtracking.app.composables.screens.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.healthtracking.app.composables.graphs.eat.CaloriesEatenGraph
import com.healthtracking.app.composables.graphs.eat.MacrosEatenGraph
import java.time.LocalDate

@Composable
fun EatStats(
    caloriesData: Map<LocalDate, Double>,
    proteinData: Map<LocalDate, Double>,
    carbsData: Map<LocalDate, Double>,
    fatsData: Map<LocalDate, Double>
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // graph of calories eaten
        CaloriesEatenGraph(caloriesData = caloriesData)

        // graph of each macro in comparison to goal (x3)
        MacrosEatenGraph(
            proteinData = proteinData,
            carbsData = carbsData,
            fatsData = fatsData
        )
    }
}