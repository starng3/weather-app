package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Coordinates(
    @SerializedName("lon")
    val longitude: BigDecimal,
    @SerializedName("lat")
    val latitude: BigDecimal
)