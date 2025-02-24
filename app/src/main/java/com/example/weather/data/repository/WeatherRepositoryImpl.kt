package com.example.weather.data.repository

import com.example.weather.data.mappers.toWeatherInfo
import com.example.weather.data.remote.WeatherApi
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.util.Resource
import com.example.weather.domain.weather.WeatherInfo
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
