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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun ItemIndicator(
    active: Boolean,
    size: Dp = 24.dp,
    activeColor: Color = MaterialTheme.colors.secondary,
    clickable: Boolean = true,
    onClick: () -> Unit = {},
) {
    val shape = MaterialTheme.shapes.medium
    Box(
        Modifier
            .padding(1.dp)
            .clip(shape)
    ) {
        val inactiveColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray
        val targetValue = if (active) activeColor else inactiveColor
        val color: Color by animateColorAsState(
            targetValue = targetValue,
            animationSpec = tween(durationMillis = 500)
        )

        Box(
            modifier = Modifier
                .size(size)
                .background(color)
                .clickable(enabled = clickable, onClick = onClick),
        )
    }
}

@Preview
@Composable
fun PreviewOn() {
    MyTheme(darkTheme = false) {
        ItemIndicator(active = true)
    }
}

@Preview
@Composable
fun PreviewOnDark() {
    MyTheme(darkTheme = true) {
        ItemIndicator(active = true)
    }
}

@Preview
@Composable
fun PreviewOff() {
    MyTheme {
        ItemIndicator(active = false)
    }
}

@Preview
@Composable
fun PreviewOffDark() {
    MyTheme(darkTheme = true) {
        ItemIndicator(active = false)
    }
}

@Preview
@Composable
fun PreviewOnWithValue() {
    MyTheme(darkTheme = false) {
        ItemIndicator(active = true, clickable = true)
    }
}
