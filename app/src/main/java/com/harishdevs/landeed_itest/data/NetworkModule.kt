package com.harishdevs.landeed_itest.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule private constructor() {
    companion object {
        val instance by lazy { NetworkModule() }
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://worldtimeapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val worldClockService by lazy { retrofit.create(WorldClockService::class.java) }
}