package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WeatherMainData(
    @SerializedName("temp")
    val temp: BigDecimal,
    @SerializedName("feels_like")
    val feelsLike: BigDecimal,
    @SerializedName("temp_min")
    val tempMin: BigDecimal,
    @SerializedName("temp_max")
    val tempMax: BigDecimal,
    @SerializedName("pressure")
    val pressure: BigDecimal,
    @SerializedName("humidity")
    val humidity: BigDecimal,
    @SerializedName("sea_level")
    val seaLevel: BigDecimal,
    @SerializedName("grnd_level")
    val groundLevel: BigDecimal
) {
}