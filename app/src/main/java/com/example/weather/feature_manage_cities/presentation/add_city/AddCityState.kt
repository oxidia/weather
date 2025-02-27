package com.example.weather.feature_manage_cities.presentation.add_city

import com.example.weather.feature_manage_cities.domain.google.Prediction

data class AddCityState(
    val searchQuery: String = "",
    val suggestions: List<Prediction> = emptyList(),
    val citiesSet: Set<String> = setOf(),
)
