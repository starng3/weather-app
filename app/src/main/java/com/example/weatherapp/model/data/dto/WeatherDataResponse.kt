package com.example.weatherapp.model.data.dto

import com.example.weatherapp.model.data.Coordinates
import com.example.weatherapp.model.data.WeatherCloudData
import com.example.weatherapp.model.data.WeatherCondition
import com.example.weatherapp.model.data.WeatherMainData
import com.example.weatherapp.model.data.WeatherRainData
import com.example.weatherapp.model.data.WeatherSnowData
import com.example.weatherapp.model.data.WeatherSystemData
import com.example.weatherapp.model.data.WeatherWindData
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class WeatherDataResponse(
    @SerializedName("coord")
    val coordinates: Coordinates,
    @SerializedName("weather")
    val weather: List<WeatherCondition>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val mainData: WeatherMainData,
    @SerializedName("visibility")
    val visibility: BigDecimal,
    @SerializedName("wind")
    val windData: WeatherWindData,
    @SerializedName("clouds")
    val cloudData: WeatherCloudData,
    @SerializedName("rain")
    val rainData: WeatherRainData,
    @SerializedName("snow")
    val snowData: WeatherSnowData,
    @SerializedName("dt")
    val dataCalcTime: BigDecimal,
    @SerializedName("sys")
    val systemData: WeatherSystemData,
    @SerializedName("timezone")
    val timezone: BigDecimal,
    @SerializedName("id")
    val cityId: Int, // Built in geo-coder is deprecated
    @SerializedName("name")
    val cityName: String, // Built in geo-coder is deprecated
    @SerializedName("cod")
    val cod: Int
)