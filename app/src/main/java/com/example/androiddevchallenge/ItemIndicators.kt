package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.math.ceil


@Composable
fun ItemIndicators(
    totalItems: Int,
    activeItems: Int,
    maxItemsPerRow: Int,
    activeColor: Color = MaterialTheme.colors.secondary
) {
    val rows = ceil(totalItems / maxItemsPerRow.toFloat()).toInt()
    Column {
        (1..rows).forEach { row ->
            val itemsBeforeThisRow = maxItemsPerRow * (row - 1)
            ItemIndicatorRow(
                itemsInRow = minOf(maxItemsPerRow, totalItems - itemsBeforeThisRow),
                activeItemsInRow = maxOf(0,activeItems - itemsBeforeThisRow),
                activeColor = activeColor,
            )
        }
    }
}

@Preview
@Composable
fun PreviewIndicatorsLessThanMax() {
    MyTheme(darkTheme = false) {
        ItemIndicators(maxItemsPerRow = 5, totalItems = 3, activeItems = 2)
    }
}

@Preview
@Composable
fun PreviewIndicatorsMultipleRows() {
    MyTheme(darkTheme = false) {
        ItemIndicators(maxItemsPerRow = 3, totalItems = 8, activeItems = 5)
    }
}
