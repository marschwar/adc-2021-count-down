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

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun TenMinutesIndicators(
    minutesActive: Int,
    itemSelectable: Boolean = false,
    onItemSelected: (Int) -> Unit = {},
) {
    ItemIndicators(
        maxItemsPerRow = 10,
        totalItems = 10,
        activeItems = minutesActive,
        activeColor = MaterialTheme.colors.primary,
        itemSelectable = itemSelectable,
        onItemSelected = onItemSelected,
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun Preview10MinutesLight() {
    MyTheme(darkTheme = false) {
        Surface {
            TenMinutesIndicators(minutesActive = 3)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PreviewTenMinutesDark() {
    MyTheme(darkTheme = true) {
        Surface {
            TenMinutesIndicators(minutesActive = 3)
        }
    }
}
