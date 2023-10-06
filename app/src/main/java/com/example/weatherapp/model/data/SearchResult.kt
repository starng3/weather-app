package com.example.weatherapp.model.data

import android.os.Parcelable
import java.math.BigDecimal

data class SearchResult(
    val city: String,
    val state: String? = null,
    val country: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal
)