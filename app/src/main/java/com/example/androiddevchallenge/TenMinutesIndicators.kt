package com.example.androiddevchallenge

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun TenMinutesIndicators(indicatorStates: List<Boolean>) {
    ItemIndicators(
        indicatorStates = indicatorStates,
        maxItemsPerRow = 10,
        activeColor = MaterialTheme.colors.primary
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun Preview10MinutesLight() {
    val indicators = (1..3).map { true } + (4..10).map { false }
    MyTheme(darkTheme = false) {
        Surface {
            TenMinutesIndicators(indicators)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PreviewTenMinutesDark() {
    val indicators = (1..3).map { true } + (4..10).map { false }
    MyTheme(darkTheme = true) {
        Surface {
            TenMinutesIndicators(indicators)
        }
    }
}
