package com.example.weather.feature_manage_cities.data.google.mappers

import com.example.weather.feature_manage_cities.data.remote.google.dto.PlaceDto
import com.example.weather.feature_manage_cities.domain.google.Location
import com.example.weather.feature_manage_cities.domain.google.PlaceData

fun PlaceDto.toPlaceData(): PlaceData {
    return PlaceData(
        name = result.name,
        placeId = result.placeId,
        location = Location(
            lat = result.geometry.location.lat,
            lng = result.geometry.location.lng
        )
    )
}
