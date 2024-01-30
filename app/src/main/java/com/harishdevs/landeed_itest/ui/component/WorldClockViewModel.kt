package com.harishdevs.landeed_itest.ui.component

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import com.harishdevs.landeed_itest.domain.CurrentTimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import java.util.Locale

class WorldClockViewModel: ViewModel() {
    private val timeFormatter by lazy { SimpleDateFormat("HH:mm:ss", Locale.getDefault()) }

    val currentTimeUseCase by lazy { CurrentTimeUseCase() }

    val timezone: MutableStateFlow<WCTimeZone> = MutableStateFlow<WCTimeZone>(WCTimeZone.IST)

    val time = timezone.mapLatest { currentTimeUseCase.invoke(it.value) }
        .flatMapLatest { calendar ->
            flow {
                while (true) {
                    emit(timeFormatter.format(calendar.time))
                    calendar.run {
                        add(Calendar.SECOND, 1)
                        kotlinx.coroutines.delay(1000)
                    }
                }
            }
        }
}

sealed class WCTimeZone(val value: String) {
    data object IST: WCTimeZone("ist")
    data object PST: WCTimeZone("pst")
}