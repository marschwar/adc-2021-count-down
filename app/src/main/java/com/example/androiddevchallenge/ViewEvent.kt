package com.example.androiddevchallenge

sealed class ViewEvent
object StartStopButtonPressed : ViewEvent()
data class MinutesChanged(val newValue: Int) : ViewEvent()
data class SecondsChanged(val newValue: Int) : ViewEvent()
