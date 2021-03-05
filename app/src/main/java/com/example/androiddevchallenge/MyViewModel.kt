package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val state: MutableLiveData<CountdownState> = MutableLiveData()
    private var minutes: Int = 1
    private var seconds: Int = 5
    private var timer: CountDownTimer? = null

    init {
        state.value = ConfigurationMode(minutes, seconds)
    }

    fun handleViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is StartStopButtonPressed -> toggleState()
            is MinutesChanged -> {
                minutes = viewEvent.newValue
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