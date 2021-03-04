package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val state: MutableLiveData<CountdownState> = MutableLiveData(CountdownState())
    private var timer: CountDownTimer? = null

    fun handleViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is ButtonPressed -> toggle()
            is MinutesChanged -> updateState {
                if (viewEvent.newValue.isDigitsOnly()) it.copy(
                    minutes = viewEvent.newValue.toInt()
                ) else it
            }
            is SecondsChanged -> updateState {
                if (viewEvent.newValue.isDigitsOnly()) it.copy(
                    seconds = viewEvent.newValue.toInt()
                ) else it
            }
        }
    }

    private fun toggle() {
        val currentTimer = timer
        if (currentTimer == null) {
            timer = object : CountDownTimer(65_000, 1_000) {
                override fun onTick(millisUntilFinished: Long) {
                    updateState { current -> current.copy(millisRemaining = millisUntilFinished.toInt()) }
                }

                override fun onFinish() {
                    updateState { current -> current.copy(millisRemaining = 0) }
                }
            }.start()
        } else {
            currentTimer.cancel()
            timer = null
            updateState { current -> current.copy(millisRemaining = 0) }
        }
    }

    private fun updateState(action: (CountdownState) -> CountdownState) {
        val current = state.value ?: CountdownState()
        state.value = action(current)
    }
}