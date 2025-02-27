package com.example.weather.feature_manage_cities.data.remote.google.dto


import com.google.gson.annotations.SerializedName

data class PlaceDto(
    @SerializedName("html_attributions")
    val htmlAttributions: List<Any>,
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: String
)
