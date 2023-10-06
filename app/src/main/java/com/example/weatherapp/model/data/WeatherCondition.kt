package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName

data class WeatherCondition(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val iconPath: String,
) {
}