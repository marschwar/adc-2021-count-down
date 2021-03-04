package com.example.androiddevchallenge

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun SixtySecondsIndicators(indicatorStates: List<Boolean>) {
    ItemIndicators(
        indicatorStates = indicatorStates,
        maxItemsPerRow = 10,
        activeColor = MaterialTheme.colors.secondary
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PreviewOneMinuteLight() {
    val indicators = (1..15).map { true } + (1..45).map { false }
    MyTheme(darkTheme = false) {
        Surface {
            SixtySecondsIndicators(indicators)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PreviewOneMinuteDark() {
    val indicators = (1..15).map { true } + (1..45).map { false }
    MyTheme(darkTheme = true) {
        Surface {
            SixtySecondsIndicators(indicators)
        }
    }
}
