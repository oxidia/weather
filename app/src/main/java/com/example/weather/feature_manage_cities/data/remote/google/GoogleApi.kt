package com.example.weather.feature_manage_cities.data.remote.google

import com.example.weather.feature_manage_cities.data.remote.google.dto.PlaceDto
import com.example.weather.feature_manage_cities.data.remote.google.dto.PlacesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {

    @GET("maps/api/place/autocomplete/json")
    suspend fun getPlacesSuggestion(
        @Query("types") types: String,
        @Query("input") input: String,
    ): PlacesDto

    @GET("maps/api/place/details/json")
    suspend fun getPlaceById(
        @Query("fields") types: String = "place_id,name,geometry",
        @Query("place_id") placeId: String,
    ): PlaceDto
}
