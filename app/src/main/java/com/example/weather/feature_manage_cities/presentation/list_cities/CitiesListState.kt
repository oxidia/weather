package com.example.weather.feature_manage_cities.presentation.list_cities

import com.example.weather.core.domain.model.City

data class CitiesListState(
    val citiesList: List<City> = emptyList(),
)
