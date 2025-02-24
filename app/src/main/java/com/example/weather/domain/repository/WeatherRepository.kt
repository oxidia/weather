package com.example.weather.domain.repository

import com.example.weather.domain.util.Resource
import com.example.weather.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): Resource<WeatherInfo>
}
