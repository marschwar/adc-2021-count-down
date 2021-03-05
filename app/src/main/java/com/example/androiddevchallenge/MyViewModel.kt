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

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val state: MutableLiveData<CountdownState> = MutableLiveData()
    private var minutes: Int = 0
    private var seconds: Int = 5
    private var timer: CountDownTimer? = null

    init {
        state.value = ConfigurationMode(minutes, seconds)
    }

    fun handleViewEvent(viewEvent: ViewEvent) {
        Log.i("ADC", "on view event $viewEvent")
        when (viewEvent) {
            StartStopButtonPressed -> toggleState()
            ResetPressed -> {
                timer = null
                state.value = ConfigurationMode(minutes, seconds)
            }
            is MinutesChanged -> {
                minutes = if (viewEvent.newValue == minutes) 0 else viewEvent.newValue
                state.value = ConfigurationMode(minutes, seconds)
            }
            is SecondsChanged -> {
                seconds = viewEvent.newValue
                state.value = ConfigurationMode(minutes, seconds)
            }
        }
    }

    private fun toggleState() {
        val currentTimer = timer
        if (currentTimer == null) {
            timer = createTimer().start()
        } else {
            currentTimer.cancel()
            timer = null
            state.value = ConfigurationMode(minutes, seconds)
        }
    }

    private fun createTimer() =
        object : CountDownTimer(calcuationDurationInMillis(), SECOND_IN_MILLIS.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                state.value = RunningMode(millisRemaining = millisUntilFinished.toInt())
            }

            override fun onFinish() {
                state.value = Finished
            }
        }

    private fun calcuationDurationInMillis(): Long =
        (minutes * 60 + seconds) * SECOND_IN_MILLIS.toLong()

    companion object {
        private const val SECOND_IN_MILLIS = 1000
    }
}
