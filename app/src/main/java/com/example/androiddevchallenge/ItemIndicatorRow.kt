package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun ItemIndicatorRow(
    itemsInRow: Int,
    activeItemsInRow: Int,
    activeColor: Color = MaterialTheme.colors.secondary
) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(Modifier.weight(1f))
        (1..itemsInRow).forEach { item ->
            val active = item <= activeItemsInRow
            ItemIndicator(value = item, active = active, activeColor = activeColor)
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun PreviewIndicatorRowAllActive() {
    MyTheme(darkTheme = false) {
        ItemIndicatorRow(itemsInRow = 5, activeItemsInRow = 5)
    }
}

@Preview
@Composable
fun PreviewIndicatorRowAllInactive() {
    MyTheme(darkTheme = false) {
        ItemIndicatorRow(itemsInRow = 5, activeItemsInRow = 0)
    }
}

@Preview
@Composable
fun PreviewIndicatorRowPartiallyActive() {
    MyTheme(darkTheme = false) {
        ItemIndicatorRow(itemsInRow = 5, activeItemsInRow = 3)
    }
}