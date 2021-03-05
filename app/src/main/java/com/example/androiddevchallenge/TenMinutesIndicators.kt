package com.example.androiddevchallenge

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun TenMinutesIndicators(minutesActive: Int) {
    ItemIndicators(
        maxItemsPerRow = 10,
        totalItems = 10,
        activeItems = minutesActive,
        activeColor = MaterialTheme.colors.primary
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
