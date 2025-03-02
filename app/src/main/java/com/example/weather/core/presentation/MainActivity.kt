package com.example.weather.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather.core.presentation.ui.theme.WeatherTheme
import com.example.weather.core.util.Screen
import com.example.weather.feature_manage_cities.presentation.add_city.AddCityScreen
import com.example.weather.feature_manage_cities.presentation.list_cities.CitiesListScreen
import com.example.weather.feature_weather.presentation.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val navController = rememberNavController()

            StatusBar(
                lightTheme = isSystemInDarkTheme()
            ) {
                enableEdgeToEdge(
                    statusBarStyle = it
                )
            }

            WeatherTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.CitiesList.route
                ) {
                    composable(
                        route = Screen.Weather.route,
                        arguments = listOf(
                            navArgument(Screen.Weather.CITY_ID_PARAM) {
                                type = NavType.IntType
                            },
                        )
                    ) {
                        WeatherScreen(
                            navController = navController
                        )
                    }

                    composable(route = Screen.CitiesList.route) {
                        CitiesListScreen(
                            navController = navController
                        )
                    }

                    composable(route = Screen.AddCity.route) {
                        AddCityScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
