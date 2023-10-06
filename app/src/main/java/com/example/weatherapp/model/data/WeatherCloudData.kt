package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WeatherCloudData(
    @SerializedName("all")
    val cloudiness: BigDecimal
) {
}