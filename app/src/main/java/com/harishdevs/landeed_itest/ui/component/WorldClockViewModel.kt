package com.harishdevs.landeed_itest.ui.component

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harishdevs.landeed_itest.domain.CurrentTimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import java.util.Locale

class WorldClockViewModel: ViewModel() {
    private val timeFormatter by lazy { SimpleDateFormat("HH:mm:ss a", Locale.getDefault()) }
    private val dateFormatter by lazy { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    val currentTimeUseCase by lazy { CurrentTimeUseCase() }

    val timezone: MutableStateFlow<WCTimeZone> = MutableStateFlow<WCTimeZone>(WCTimeZone.PST)

    val time = timezone.mapLatest { currentTimeUseCase.invoke(it.value) }
        .flatMapLatest { calendar ->
            flow {
                while (true) {
                    emit("${dateFormatter.format(calendar.time)}\n${timeFormatter.format(calendar.time)}")
                    calendar.run {
                        add(Calendar.SECOND, 1)
                        kotlinx.coroutines.delay(1000)
                    }
                }
            }
        }
        .stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = "--:--:--")
}

sealed class WCTimeZone(val value: String) {
    data object IST: WCTimeZone("Asia/Kolkata")
    data object PST: WCTimeZone("America/Los_Angeles")
}