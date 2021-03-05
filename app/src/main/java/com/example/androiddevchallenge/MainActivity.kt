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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                viewModel.state.observeAsState().value?.let { state ->
                    CountdownTimerApp(
                        currentState = state,
                        onStartStop = { viewModel.handleViewEvent(StartStopButtonPressed) },
                        onReset = { viewModel.handleViewEvent(ResetPressed) },
                        onMinutesChanged = { s -> viewModel.handleViewEvent(MinutesChanged(s)) },
                        onSecondsChanged = { s -> viewModel.handleViewEvent(SecondsChanged(s)) },
                    )
                }
            }
        }
    }
}

@Composable
fun CountdownTimerApp(
    currentState: CountdownState,
    onStartStop: () -> Unit = {},
    onReset: () -> Unit = {},
    onMinutesChanged: (Int) -> Unit = {},
    onSecondsChanged: (Int) -> Unit = {},
) {
    Surface(color = MaterialTheme.colors.background) {
        when (currentState) {
            Finished -> TimeIsUp(onReset = onReset)
            is CountdownStateWithTime -> Timer(
                currentState = currentState,
                onStartStop = onStartStop
            )
        }
    }
}


@Preview(widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewConfigMode() {
    MyTheme {
        CountdownTimerApp(ConfigurationMode(minutes = 3, seconds = 34))
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewFinished() {
    MyTheme {
        CountdownTimerApp(Finished)
    }
}
