package com.example.weatherapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.api.OpenWeatherRepoImpl
import com.example.weatherapp.model.BundleConstants
import com.example.weatherapp.view.screens.SearchResultsScreen
import com.example.weatherapp.view.theme.WeatherAppTheme
import com.example.weatherapp.viewmodel.SearchResultViewModel
import com.example.weatherapp.viewmodel.SearchResultViewModelFactory

class SearchResultActivity: ComponentActivity() {
    private lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SearchResultsScreen(
                        searchResults = remember {
                            searchResultViewModel.searchResults
                        },
                        onItemClicked = { searchResult ->
                            val intent = Intent()
                            intent.putExtra(BundleConstants.CITY_LATITUDE_KEY, searchResult.latitude)
                            intent.putExtra(BundleConstants.CITY_LONGITUDE_KEY, searchResult.longitude)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    )
                }
            }
        }

        intent.extras?.let {
            val city = it.getString(BundleConstants.CITY_NAME_KEY)

            searchResultViewModel = ViewModelProvider(
                this,
                SearchResultViewModelFactory(OpenWeatherRepoImpl())
            )[SearchResultViewModel::class.java]

            city?.let { searchResultViewModel.searchForCity(city) }
        }
    }
}