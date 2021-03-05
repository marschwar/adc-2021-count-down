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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
    onMinutesChanged: (Int) -> Unit = {},
    onSecondsChanged: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        val configurationMode = currentState is ConfigurationMode
        SectionHeader("Minutes")
        TenMinutesIndicators(
            currentState.minutes,
            itemSelectable = configurationMode,
            onItemSelected = onMinutesChanged
        )
        SectionHeader("Seconds")
        SixtySecondsIndicators(
            currentState.seconds,
            itemSelectable = configurationMode,
            onItemSelected = onSecondsChanged
        )
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
                    if (configurationMode) Icons.Filled.PlayArrow else Icons.Filled.Stop
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
