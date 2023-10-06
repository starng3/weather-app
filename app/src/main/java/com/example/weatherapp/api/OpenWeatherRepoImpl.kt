package com.example.weatherapp.api

import com.example.weatherapp.api.restclient.OpenWeatherApiClient
import com.example.weatherapp.model.data.dto.CoordinatesResponse
import com.example.weatherapp.model.data.dto.WeatherDataResponse
import com.example.weatherapp.model.repo.OpenWeatherRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal

class OpenWeatherRepoImpl : OpenWeatherRepo {
    companion object {
        // In future, can be expanded to accept a user set limit
        const val SEARCH_LIMIT = 5
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(OpenWeatherApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val openWeatherApiClient = retrofit.create(OpenWeatherApiClient::class.java)

    override suspend fun getCurrentWeatherData(
        latitude: BigDecimal,
        longitude: BigDecimal
    ): Result<WeatherDataResponse> {
        return runCatching {
            openWeatherApiClient.getCurrentWeatherData(
                latitude,
                longitude,
                "imperial", // Hard coded as imperial units for simplicity, if more time can support multiple
                OpenWeatherApiConstants.OPEN_WEATHER_API_KEY
            )
        }
    }

    override suspend fun getCoordinates(cityName: String): Result<List<CoordinatesResponse>> {
        return runCatching {
            openWeatherApiClient.getCoordinates(
                cityName,
                SEARCH_LIMIT,
                OpenWeatherApiConstants.OPEN_WEATHER_API_KEY
            )
        }
    }
}