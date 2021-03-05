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