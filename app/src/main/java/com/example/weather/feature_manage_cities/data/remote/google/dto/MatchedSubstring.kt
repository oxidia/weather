package com.example.weather.feature_manage_cities.data.remote.google.dto


import com.google.gson.annotations.SerializedName

data class MatchedSubstring(
    @SerializedName("length")
    val length: Int,
    @SerializedName("offset")
    val offset: Int
)
