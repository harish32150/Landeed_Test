package com.harishdevs.landeed_itest.data

import com.google.gson.annotations.SerializedName

data class WorldClockResponse(
    @SerializedName("datetime") val dateTime: String
)