package com.example.androiddevchallenge

sealed class ViewEvent
object ButtonPressed : ViewEvent()
data class MinutesChanged(val newValue: String) : ViewEvent()
data class SecondsChanged(val newValue: String) : ViewEvent()
