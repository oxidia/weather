package com.example.weather.core.util


sealed class Screen(val route: String) {
    data object Weather : Screen("weather_screen/{cityId}") {

        const val CITY_ID_PARAM = "cityId"

        fun passCityId(cityId: Int): String {
            return "weather_screen/$cityId"
        }
    }

    data object CitiesList : Screen("cities_list_screen")

    data object AddCity : Screen("add_city_screen")
}
