package com.example.weatherapp.api.restclient

import com.example.weatherapp.model.data.dto.CoordinatesResponse
import com.example.weatherapp.model.data.dto.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigDecimal

interface OpenWeatherApiClient {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: BigDecimal,
        @Query("lon") longitude: BigDecimal,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): WeatherDataResponse

    @GET("geo/1.0/direct")
    suspend fun getCoordinates(
        @Query("q") cityName: String,
        @Query("limit") searchLimit: Int,
        @Query("appid") apiKey: String
    ): List<CoordinatesResponse>
}