package com.harishdevs.landeed_itest.data

import retrofit2.http.GET
import retrofit2.http.Path

interface WorldClockService {
    //curl "http://worldtimeapi.org/api/timezone/Asia/Kolkata"
    @GET("/api/timezone/{timezone}")
    suspend fun currentTime(@Path("timezone") timezone: String): WorldClockResponse
}