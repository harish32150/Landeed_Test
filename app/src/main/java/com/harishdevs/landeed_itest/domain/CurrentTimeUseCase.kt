package com.harishdevs.landeed_itest.domain

import java.util.Calendar

class CurrentTimeUseCase {
    suspend operator fun invoke(timezone: String): Calendar {
        return Calendar.getInstance()
    }
}