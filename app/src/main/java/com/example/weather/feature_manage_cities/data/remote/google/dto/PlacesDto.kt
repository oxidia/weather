package com.example.weather.feature_manage_cities.data.remote.google.dto


import com.google.gson.annotations.SerializedName

data class PlacesDto(
    @SerializedName("predictions")
    val predictions: List<Prediction>,
    @SerializedName("status")
    val status: String,
)
