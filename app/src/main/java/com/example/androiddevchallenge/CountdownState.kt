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

sealed class CountdownState

object Finished : CountdownState()

abstract class CountdownStateWithTime : CountdownState() {
    abstract val minutes: Int
    abstract val seconds: Int
}

data class ConfigurationMode(
    override val minutes: Int = 0,
    override val seconds: Int = 10
) : CountdownStateWithTime()

data class RunningMode(
    val millisRemaining: Int = 0,
) : CountdownStateWithTime() {

    private val seconsRemaining: Int = millisRemaining / 1000

    override val minutes: Int = (seconsRemaining - 1) / 60
    override val seconds: Int = seconsRemaining - (minutes * 60)
}
