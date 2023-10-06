package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WeatherWindData(
    @SerializedName("speed")
    val speed: BigDecimal,
    @SerializedName("deg")
    val deg: BigDecimal,
    @SerializedName("gust")
    val gust: BigDecimal
)