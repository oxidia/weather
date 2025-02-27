package com.example.weather.core.util


sealed class Screen(val route: String) {
    data object Weather : Screen("weather_screen?lat={lat}&lng={lng}") {

        const val LATITUDE_PARAM = "lat"
        const val LONGITUDE_PARAM = "lng"

        fun passCoordinates(lat: Double, lng: Double): String {
            return "weather_screen?lat=$lat&lng=$lng"
        }
    }

    data object CitiesList : Screen("cities_list_screen")

    data object AddCity : Screen("add_city_screen")
}
