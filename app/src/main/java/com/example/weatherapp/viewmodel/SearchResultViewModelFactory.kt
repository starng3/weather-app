package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.model.repo.OpenWeatherRepo

class SearchResultViewModelFactory(
    private val openWeatherRepo: OpenWeatherRepo
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            SearchResultViewModel(this.openWeatherRepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}