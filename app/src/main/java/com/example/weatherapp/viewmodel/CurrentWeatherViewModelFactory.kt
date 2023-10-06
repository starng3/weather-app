package com.example.weatherapp.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.model.repo.OpenWeatherRepo

class CurrentWeatherViewModelFactory(
    private val openWeatherRepo: OpenWeatherRepo,
    private val dataStore: DataStore<Preferences>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java)) {
            CurrentWeatherViewModel(this.openWeatherRepo, this.dataStore) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}