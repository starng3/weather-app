package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.data.SearchResult
import com.example.weatherapp.model.repo.OpenWeatherRepo
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val logger = KotlinLogging.logger {}

class SearchResultViewModel(
    private val openWeatherRepo: OpenWeatherRepo,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ViewModel() {
    var searchResults = mutableStateListOf<SearchResult>()

    fun searchForCity(cityName: String) {
        coroutineScope.launch {
            openWeatherRepo.getCoordinates(cityName)
                .onSuccess { coordinatesResponse ->
                    searchResults.addAll(coordinatesResponse.map {
                        SearchResult(
                            city = it.name,
                            state = it.state,
                            country = it.country,
                            latitude = it.latitude,
                            longitude = it.longitude
                        )
                    })
                }.onFailure { error ->
                    logger.error { "getCoordinates failed: ${error.localizedMessage}" }
                }
        }
    }
}