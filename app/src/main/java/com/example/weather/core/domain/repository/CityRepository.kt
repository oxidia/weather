package com.example.weather.core.domain.repository

import com.example.weather.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun insertCity(city: City)

    fun getCities(): Flow<List<City>>

    suspend fun getCityById(id: Int): City?

    suspend fun deleteCity(city: City)
}
