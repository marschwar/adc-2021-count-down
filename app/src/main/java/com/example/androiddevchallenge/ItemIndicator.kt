package com.example.androiddevchallenge

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun ItemIndicator(
    value: Int,
    active: Boolean,
    size: Dp = 24.dp,
    activeColor: Color = MaterialTheme.colors.secondary,
    flashValueOnClick: Boolean = false,
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
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .background(color),

        ) {
            if (flashValueOnClick) {
                Text(text = value.toString())
            }
        }
    }
}

@Preview
@Composable
fun PreviewOn() {
    MyTheme(darkTheme = false) {
        ItemIndicator(value = 25, active = true)
    }
}

@Preview
@Composable
fun PreviewOnDark() {
    MyTheme(darkTheme = true) {
        ItemIndicator(value = 25, active = true)
    }
}

@Preview
@Composable
fun PreviewOff() {
    MyTheme {
        ItemIndicator(value = 25, active = false)
    }
}

@Preview
@Composable
fun PreviewOffDark() {
    MyTheme(darkTheme = true) {
        ItemIndicator(value = 25, active = false)
    }
}

@Preview
@Composable
fun PreviewOnWithValue() {
    MyTheme(darkTheme = false) {
        ItemIndicator(value = 25, active = true, flashValueOnClick = true)
    }
}
