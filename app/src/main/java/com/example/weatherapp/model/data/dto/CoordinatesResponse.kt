package com.example.weatherapp.model.data.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CoordinatesResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("local_names")
    val localNames: Any, // Out of scope for our requirements, not needed to create a custom class for local names
    @SerializedName("lat")
    val latitude: BigDecimal,
    @SerializedName("lon")
    val longitude: BigDecimal,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String
)