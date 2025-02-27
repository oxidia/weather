package com.example.weather.feature_manage_cities.presentation.list_cities

import com.example.weather.core.domain.model.City

sealed class CitiesListEvent {
    data object OnSearchButtonClick : CitiesListEvent()
    data class OnDeleteCity(val city: City) : CitiesListEvent()
    data object PopBackStack : CitiesListEvent()
}
