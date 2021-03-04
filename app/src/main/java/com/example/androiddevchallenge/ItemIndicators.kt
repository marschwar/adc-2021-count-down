package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.math.ceil


@Composable
fun ItemIndicators(
    indicatorStates: List<Boolean>,
    maxItemsPerRow: Int,
    activeColor: Color = MaterialTheme.colors.secondary
) {
    if (indicatorStates.isEmpty()) {
        return
    }
    val chunked = indicatorStates.chunked(maxItemsPerRow)
    Column {
        chunked.forEach { row ->
            ItemIndicatorRow(row, activeColor)
        }
    }
}

@Composable
private fun ItemIndicatorRow(row: List<Boolean>, activeColor: Color) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(Modifier.weight(1f))
        row.forEach { state ->
            ItemIndicator(on = state, activeColor = activeColor)
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun PreviewIndicatorsLessThanMax() {
    MyTheme(darkTheme = false) {
        ItemIndicators(listOf(true, true, false), 5)
    }
}

@Preview
@Composable
fun PreviewIndicatorsMultipleRows() {
    MyTheme(darkTheme = false) {
        ItemIndicators(listOf(true, true, false, true, true, true, false, true), 3)
    }
}
