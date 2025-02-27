package com.example.weather.feature_weather.domain.repository

import com.example.weather.core.util.Resource
import com.example.weather.feature_weather.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): Resource<WeatherInfo>
}
