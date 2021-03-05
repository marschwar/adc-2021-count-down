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
    firstItemValue: Int,
    itemsInRow: Int,
    activeItemsInRow: Int,
    itemSelectable: Boolean = false,
    onItemSelected: (Int) -> Unit = {},
    activeColor: Color = MaterialTheme.colors.secondary
) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(Modifier.weight(1f))
        (1..itemsInRow).forEach { item ->
            val active = item <= activeItemsInRow
            ItemIndicator(
                active = active,
                activeColor = activeColor,
                clickable = itemSelectable,
                onClick = { onItemSelected(firstItemValue + item - 1) }
            )
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun PreviewIndicatorRowAllActive() {
    MyTheme(darkTheme = false) {
        ItemIndicatorRow(firstItemValue = 1, itemsInRow = 5, activeItemsInRow = 5)
    }
}

@Preview
@Composable
fun PreviewIndicatorRowAllInactive() {
    MyTheme(darkTheme = false) {
        ItemIndicatorRow(firstItemValue = 1, itemsInRow = 5, activeItemsInRow = 0)
    }
}

@Preview
@Composable
fun PreviewIndicatorRowPartiallyActive() {
    MyTheme(darkTheme = false) {
        ItemIndicatorRow(firstItemValue = 1, itemsInRow = 5, activeItemsInRow = 3)
    }
}
