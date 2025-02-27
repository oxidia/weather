package com.example.weather.feature_manage_cities.data.remote.google.dto


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("name")
    val name: String,
    @SerializedName("place_id")
    val placeId: String
)
