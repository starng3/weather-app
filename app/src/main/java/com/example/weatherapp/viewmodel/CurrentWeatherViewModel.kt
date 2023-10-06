package com.example.weatherapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.PreferencesKeys
import com.example.weatherapp.model.data.WeatherDisplayData
import com.example.weatherapp.model.repo.OpenWeatherRepo
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}
class CurrentWeatherViewModel(
    private val openWeatherRepo: OpenWeatherRepo,
    private val dataStore: DataStore<Preferences>,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ViewModel() {
    var currentWeatherDisplayData = mutableStateOf<WeatherDisplayData?>(null)

    fun getCurrentWeather(latitude: BigDecimal, longitude: BigDecimal) {
        coroutineScope.launch {
            openWeatherRepo.getCurrentWeatherData(latitude, longitude)
            .onSuccess { response ->
                currentWeatherDisplayData.value = WeatherDisplayData(
                    cityName = response.cityName,
                    currentTemp = response.mainData.temp,
                    feelsLikeTemp = response.mainData.feelsLike,
                    weatherIconCode = response.weather.first().iconPath //If more time, would account for multiple weather params
                )
                saveLastSearchedCity(latitude, longitude)
            }.onFailure { error ->
                error.message?.let { logger.error { "getWeatherData failed: ${error.localizedMessage}" } }
            }
        }
    }

    private suspend fun saveLastSearchedCity(latitude: BigDecimal, longitude: BigDecimal) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_LATITUDE] = latitude.toString()
            preferences[PreferencesKeys.LAST_LONGITUDE] = longitude.toString()
        }
    }
}