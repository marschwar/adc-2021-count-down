package com.example.androiddevchallenge

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun SixtySecondsIndicators(activeSeconds: Int) {
    ItemIndicators(
        maxItemsPerRow = 10,
        totalItems = 60,
        activeItems = activeSeconds,
        activeColor = MaterialTheme.colors.secondary
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PreviewOneMinuteLight() {
    MyTheme(darkTheme = false) {
        Surface {
            SixtySecondsIndicators(activeSeconds = 14)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PreviewOneMinuteDark() {
    MyTheme(darkTheme = true) {
        Surface {
            SixtySecondsIndicators(activeSeconds = 47)
        }
    }
}
