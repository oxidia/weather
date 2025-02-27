package com.example.weather.feature_weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(

    @SerializedName("hourly")
    val hourlyWeatherData: WeatherDataDto
)
