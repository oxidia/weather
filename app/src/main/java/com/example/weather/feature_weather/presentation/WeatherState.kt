package com.example.weather.feature_weather.presentation

import com.example.weather.core.domain.model.City
import com.example.weather.feature_weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val city: City? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
