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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                viewModel.state.observeAsState().value?.let { state ->
                    MyApp(
                        currentState = state,
                        onClick = { viewModel.handleViewEvent(ButtonPressed) },
                        onMinutesChanged = { s -> viewModel.handleViewEvent(MinutesChanged(s)) },
                        onSecondsChanged = { s -> viewModel.handleViewEvent(SecondsChanged(s)) },
                    )
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(
    currentState: CountdownState = CountdownState(),
    onClick: () -> Unit = {},
    onMinutesChanged: (String) -> Unit = {},
    onSecondsChanged: (String) -> Unit = {}
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.padding(32.dp)
        ) {
            SectionHeader("Minutes")
            TenMinutesIndicators(currentState.minutesIndicators)
            SectionHeader("Seconds")
            SixtySecondsIndicators(currentState.secondsIndicators)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(48.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onClick,
                    shape = CircleShape,
                    modifier = Modifier.size(64.dp),
                ) {
                    val icon = if (currentState.started) Icons.Filled.Stop else Icons.Filled.PlayArrow
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "start-stop"
                    )
                }
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

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreviewStarted() {
    MyTheme(darkTheme = true) {
        MyApp(CountdownState(millisRemaining = 72_000))
    }
}
