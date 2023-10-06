package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WeatherRainData(
    @SerializedName("1h")
    val rain1hVolume: BigDecimal,
    @SerializedName("3h")
    val rain3hVolume: BigDecimal
)