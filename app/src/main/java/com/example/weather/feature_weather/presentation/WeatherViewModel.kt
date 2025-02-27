package com.example.weather.feature_weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.core.domain.location.LocationTracker
import com.example.weather.core.util.Resource
import com.example.weather.core.util.Screen
import com.example.weather.feature_weather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    init {
        val latitude =
            savedStateHandle.get<String>(Screen.Weather.LATITUDE_PARAM)
        val longitude =
            savedStateHandle.get<String>(Screen.Weather.LONGITUDE_PARAM)

        if (latitude != null && longitude != null) {
            loadWeatherInfo(
                latitude = latitude.toDouble(),
                longitude = longitude.toDouble()
            )
        }
    }

    private fun loadWeatherInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {

            state = state.copy(
                isLoading = true,
                error = null
            )

            val response = weatherRepository.getWeatherData(
                latitude = latitude,
                longitude = longitude
            )

            state = when (response) {
                is Resource.Success -> {
                    state.copy(
                        isLoading = false,
                        error = null,
                        weatherInfo = response.data,
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        isLoading = false,
                        weatherInfo = null,
                        error = response.message
                    )
                }
            }
        }
    }

    fun loadWeatherInfo() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()
                ?.let { location ->
                    loadWeatherInfo(location.latitude, location.longitude)
                } ?: kotlin.run {
                state = state.copy(
                    error = "Cannot retrieve location",
                    isLoading = false
                )
            }
        }
    }
}
