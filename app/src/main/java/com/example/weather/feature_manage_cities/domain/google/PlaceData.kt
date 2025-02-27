package com.example.weather.feature_manage_cities.domain.google

class PlaceData(
    val name: String,
    val placeId: String,
    val location: Location? = null
)
