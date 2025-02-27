package com.example.weather.feature_manage_cities.presentation.add_city

import com.example.weather.feature_manage_cities.domain.google.Prediction

sealed class AddCityEvent {
    data class OnSearchQueryChange(val value: String) : AddCityEvent()
    data class OnCitiesListChange(val values: List<Prediction>) : AddCityEvent()
    data object OnCancelClick : AddCityEvent()
    data class OnAddCityClick(val prediction: Prediction) : AddCityEvent()
}
