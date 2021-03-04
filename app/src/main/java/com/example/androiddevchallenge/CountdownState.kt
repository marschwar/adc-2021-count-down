package com.example.androiddevchallenge

data class CountdownState(
    val minutes: Int = 0,
    val seconds: Int = 10,
    val millisRemaining: Int = 0
) {

    val started: Boolean = millisRemaining > 0

    val secondsIndicators: List<Boolean> =
        ((millisRemaining / 1000) % 60).let { active ->
            (1..active).map { true } + (active..59).map { false }
        }

    val minutesIndicators: List<Boolean> =
        ((millisRemaining / 1000) / 60).let { active ->
            (1..active).map { true } + (active..9).map { false }
        }
}