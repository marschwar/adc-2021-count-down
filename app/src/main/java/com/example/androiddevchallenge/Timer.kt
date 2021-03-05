package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun Timer(
    currentState: CountdownStateWithTime,
    onStartStop: () -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        SectionHeader("Minutes")
        TenMinutesIndicators(currentState.minutes)
        SectionHeader("Seconds")
        SixtySecondsIndicators(currentState.seconds)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(48.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onStartStop,
                shape = CircleShape,
                modifier = Modifier.size(64.dp),
            ) {
                val icon =
                    if (currentState is RunningMode) Icons.Filled.Stop else Icons.Filled.PlayArrow
                Icon(
                    imageVector = icon,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "start-stop"
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h3
    )
}

@Preview
@Composable
fun LightPreviewConfiguration() {
    MyTheme {
        Surface {
            Timer(ConfigurationMode(minutes = 3, seconds = 34))
        }
    }
}

@Preview
@Composable
fun DarkPreviewRunnig() {
    MyTheme(darkTheme = true) {
        Surface {
            Timer(RunningMode(millisRemaining = 72_000))
        }
    }
}
