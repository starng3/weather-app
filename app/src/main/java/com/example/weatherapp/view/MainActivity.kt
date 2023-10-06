package com.example.weatherapp.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.api.OpenWeatherRepoImpl
import com.example.weatherapp.model.BundleConstants
import com.example.weatherapp.model.PreferencesKeys
import com.example.weatherapp.model.data.Coordinates
import com.example.weatherapp.view.screens.WeatherScreen
import com.example.weatherapp.view.theme.WeatherAppTheme
import com.example.weatherapp.viewmodel.CurrentWeatherViewModel
import com.example.weatherapp.viewmodel.CurrentWeatherViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.math.BigDecimal

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "savedStore")
class MainActivity : ComponentActivity() {
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    @SuppressLint("MissingPermission") // No need for permission as this is within the permission granted callback
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                    loadWeatherDataFromCurrentLocation(it)
                }
            }
        }

    private val searchContent = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            intent?.extras?.let {
                val longitude = it.getSerializable(BundleConstants.CITY_LONGITUDE_KEY) as BigDecimal
                val latitude = it.getSerializable(BundleConstants.CITY_LATITUDE_KEY) as BigDecimal

                currentWeatherViewModel.getCurrentWeather(latitude, longitude)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    WeatherScreen(
                        weatherDisplayData = remember {
                            currentWeatherViewModel.currentWeatherDisplayData
                        },
                        onSearchButtonClicked = { cityName ->
                            val intent = Intent(this, SearchResultActivity::class.java)
                            intent.putExtra(BundleConstants.CITY_NAME_KEY, cityName)
                            searchContent.launch(intent)
                        }
                    )
                }
            }
        }

        currentWeatherViewModel = ViewModelProvider(
            this,
            CurrentWeatherViewModelFactory(OpenWeatherRepoImpl(), applicationContext.dataStore)
        )[CurrentWeatherViewModel::class.java]

        /**
         * If User has searched for a city before, load the last searched city
         * Else: load from current location (if location permissions are granted)
         */
        lifecycleScope.launch {
             val coordinates = getCoordinatesFromDataStore()
             if (coordinates != null) {
                currentWeatherViewModel.getCurrentWeather(coordinates.latitude, coordinates.longitude)
             } else {
                 checkForLocationPermissions()
             }
        }
    }

    private fun checkForLocationPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                    loadWeatherDataFromCurrentLocation(it)
                }

            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun loadWeatherDataFromCurrentLocation(location: Location) {
        currentWeatherViewModel.getCurrentWeather(
            latitude = BigDecimal(location.latitude),
            longitude = BigDecimal(location.longitude)
        )
    }

    private suspend fun getCoordinatesFromDataStore(): Coordinates? {
        val preferences = dataStore.data.first()
        val longitude = preferences[PreferencesKeys.LAST_LONGITUDE] ?: ""
        val latitude = preferences[PreferencesKeys.LAST_LATITUDE] ?: ""
        if (longitude.isEmpty() || latitude.isEmpty()) {
            return null
        }
        return Coordinates(
            longitude = BigDecimal(longitude),
            latitude = BigDecimal(latitude)
        )
    }
}