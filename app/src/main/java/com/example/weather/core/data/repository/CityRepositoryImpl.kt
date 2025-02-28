package com.example.weather.core.data.repository

import com.example.weather.core.data_source.CityDao
import com.example.weather.core.domain.model.City
import com.example.weather.core.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

class CityRepositoryImpl(
    private val cityDao: CityDao,
) : CityRepository {

    override suspend fun insertCity(city: City) {
        cityDao.insertCity(city)
    }

    override fun getCities(): Flow<List<City>> {
        return cityDao.getCities()
    }

    override suspend fun getCityById(id: Int): City? {
        return cityDao.getCityById(id)
    }

    override suspend fun deleteCity(city: City) {
        cityDao.deleteCity(city)
    }
}
