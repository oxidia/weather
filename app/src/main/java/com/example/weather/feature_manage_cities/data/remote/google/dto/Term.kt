package com.example.weather.feature_manage_cities.data.remote.google.dto


import com.google.gson.annotations.SerializedName

data class Term(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("value")
    val value: String,
)
