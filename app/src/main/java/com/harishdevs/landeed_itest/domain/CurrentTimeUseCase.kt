package com.harishdevs.landeed_itest.domain

import android.icu.text.SimpleDateFormat
import com.harishdevs.landeed_itest.data.NetworkModule
import java.util.Calendar
import java.util.Locale

class CurrentTimeUseCase {
    suspend operator fun invoke(timezone: String): Calendar =
        NetworkModule.instance.worldClockService.currentTime(timezone)
            .dateTime
            .let(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())::parse)
            .let { date -> Calendar.getInstance().also { it.time = date} }
}