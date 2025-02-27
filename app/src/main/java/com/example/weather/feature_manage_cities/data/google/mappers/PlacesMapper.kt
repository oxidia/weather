package com.example.weather.feature_manage_cities.data.google.mappers

import com.example.weather.feature_manage_cities.data.remote.google.dto.PlacesDto
import com.example.weather.feature_manage_cities.domain.google.Prediction


fun PlacesDto.toPredictionsData(): List<Prediction> {
    return predictions.map { prediction ->
        Prediction(
            description = prediction.description,
            placeId = prediction.placeId,
        )
    }
}
