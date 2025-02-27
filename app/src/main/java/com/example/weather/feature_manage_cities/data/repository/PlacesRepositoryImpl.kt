package com.example.weather.feature_manage_cities.data.repository

import com.example.weather.feature_manage_cities.data.google.mappers.toPlaceData
import com.example.weather.feature_manage_cities.data.google.mappers.toPredictionsData
import com.example.weather.feature_manage_cities.data.remote.google.GoogleApi
import com.example.weather.feature_manage_cities.domain.google.PlaceData
import com.example.weather.feature_manage_cities.domain.google.Prediction
import com.example.weather.feature_manage_cities.domain.repository.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val api: GoogleApi,
) : PlacesRepository {

    override suspend fun getPredictions(
        input: String,
    ): List<Prediction> {
        val places = api.getPlacesSuggestion(input = input, types = "(cities)")

        return places.toPredictionsData()
    }

    override suspend fun getPlaceById(placeId: String): PlaceData {
        val place = api.getPlaceById(
            placeId = placeId
        )

        println("GetPlaceById $placeId")

        if (place != null) {
            println("GetPlaceById ${place.result.name}")
        } else {
            println("GetPlaceById place is null")
        }

        return place.toPlaceData()
    }
}
