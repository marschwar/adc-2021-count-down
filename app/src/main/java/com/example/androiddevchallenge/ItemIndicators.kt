/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    itemSelectable: Boolean = false,
    onItemSelected: (Int) -> Unit = {},
    activeColor: Color = MaterialTheme.colors.secondary
) {
    val rows = ceil(totalItems / maxItemsPerRow.toFloat()).toInt()
    Column {
        (1..rows).forEach { row ->
            val itemsBeforeThisRow = maxItemsPerRow * (row - 1)
            ItemIndicatorRow(
                firstItemValue = itemsBeforeThisRow + 1,
                itemsInRow = minOf(maxItemsPerRow, totalItems - itemsBeforeThisRow),
                activeItemsInRow = maxOf(0, activeItems - itemsBeforeThisRow),
                activeColor = activeColor,
                itemSelectable = itemSelectable,
                onItemSelected = onItemSelected,
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
