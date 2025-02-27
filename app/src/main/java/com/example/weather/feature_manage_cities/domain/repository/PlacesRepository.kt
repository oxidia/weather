package com.example.weather.feature_manage_cities.domain.repository

import com.example.weather.feature_manage_cities.domain.google.PlaceData
import com.example.weather.feature_manage_cities.domain.google.Prediction

interface PlacesRepository {

    suspend fun getPredictions(input: String): List<Prediction>
    suspend fun getPlaceById(placeId: String): PlaceData
}
