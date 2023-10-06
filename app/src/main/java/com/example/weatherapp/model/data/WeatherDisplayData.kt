package com.example.weatherapp.model.data

import java.math.BigDecimal

const val BASE_IMG_URL = "https://openweathermap.org/img/wn/"

data class WeatherDisplayData(
    val cityName: String,
    val currentTemp: BigDecimal,
    val feelsLikeTemp: BigDecimal,
    val weatherIconCode: String
) {
    val weatherIconPath = "${BASE_IMG_URL}${weatherIconCode}@4x.png"
}