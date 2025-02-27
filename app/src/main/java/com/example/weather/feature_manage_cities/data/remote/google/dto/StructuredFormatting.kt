package com.example.weather.feature_manage_cities.data.remote.google.dto


import com.google.gson.annotations.SerializedName

data class StructuredFormatting(
    @SerializedName("main_text")
    val mainText: String,
    @SerializedName("main_text_matched_substrings")
    val mainTextMatchedSubstrings: List<MainTextMatchedSubstring>,
    @SerializedName("secondary_text")
    val secondaryText: String,
)
