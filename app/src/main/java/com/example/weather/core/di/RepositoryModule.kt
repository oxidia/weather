package com.example.weather.core.di

import com.example.weather.feature_manage_cities.data.repository.PlacesRepositoryImpl
import com.example.weather.feature_manage_cities.domain.repository.PlacesRepository
import com.example.weather.feature_weather.data.repository.WeatherRepositoryImpl
import com.example.weather.feature_weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepository: WeatherRepositoryImpl,
    ): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindPlacesRepository(
        placesRepository: PlacesRepositoryImpl,
    ): PlacesRepository
}
