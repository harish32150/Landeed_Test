package com.harishdevs.landeed_itest.ui.component

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import java.util.UUID
import java.util.concurrent.TimeUnit

class TimerViewModel : ViewModel() {

    private val _timerListState = MutableStateFlow<MutableList<TimerInfo>>(mutableListOf())
    val timerListState: StateFlow<List<TimerInfo>> = _timerListState.asStateFlow()

    fun addTimer(hours: Int, minutes: Int, seconds: Int) {
        _timerListState.update { list -> list.apply { add(TimerInfo(hours, minutes, seconds)) } }
    }

    fun removeTimer(timerInfo: TimerInfo) {
        _timerListState.update { list -> list.apply { removeIf { it.id == timerInfo.id } } }
    }
}

class TimerInfo(
    val id: String,
    val startTime: Long,
    val endTime: Long,
) {
    constructor(hours: Int, minutes: Int, seconds: Int) : this(
        id = UUID.randomUUID().toString(),
        startTime = System.currentTimeMillis(),
        endTime = System.currentTimeMillis()
            .plus(TimeUnit.HOURS.toMillis(hours.toLong()))
            .plus(TimeUnit.MINUTES.toMillis(minutes.toLong()))
            .plus(TimeUnit.SECONDS.toMillis(seconds.toLong()))
    )

    val millisRemaining: Long
        get() = (endTime - System.currentTimeMillis()).coerceAtLeast(0L)

    val timerFlow = flow {
        while (millisRemaining > 0) {
            emit((endTime - System.currentTimeMillis()).coerceAtLeast(0L))
            delay(1000)
        }
        emit(0L)
    }
}