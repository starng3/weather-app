package com.example.weatherapp.model

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val LAST_LATITUDE = stringPreferencesKey("last_latitude")
    val LAST_LONGITUDE = stringPreferencesKey("last_longitude")
}