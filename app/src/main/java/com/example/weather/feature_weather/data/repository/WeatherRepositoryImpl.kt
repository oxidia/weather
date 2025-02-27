package com.example.weather.feature_weather.data.repository

import com.example.weather.feature_weather.data.mappers.toWeatherInfo
import com.example.weather.feature_weather.data.remote.WeatherApi
import com.example.weather.feature_weather.domain.repository.WeatherRepository
import com.example.weather.core.util.Resource
import com.example.weather.feature_weather.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): Resource<WeatherInfo> {
        try {

            val data = api.getWeatherData(
                latitude = latitude,
                longitude = longitude,
            )

            return Resource.Success(data = data.toWeatherInfo())
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "Unexpected error")
        }
    }
}
