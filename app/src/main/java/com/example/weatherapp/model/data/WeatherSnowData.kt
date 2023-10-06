package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WeatherSnowData(
    @SerializedName("1h")
    val snow1hVolume: BigDecimal,
    @SerializedName("3h")
    val snow3hVolume: BigDecimal
)