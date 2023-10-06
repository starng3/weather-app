package com.example.weatherapp.model.repo

import com.example.weatherapp.model.data.dto.CoordinatesResponse
import com.example.weatherapp.model.data.dto.WeatherDataResponse
import java.math.BigDecimal

interface OpenWeatherRepo {
    suspend fun getCurrentWeatherData(latitude: BigDecimal, longitude: BigDecimal): Result<WeatherDataResponse>

    /**
     * In future, can be adjusted to search by zip code, state
     */
    suspend fun getCoordinates(cityName: String): Result<List<CoordinatesResponse>>
}