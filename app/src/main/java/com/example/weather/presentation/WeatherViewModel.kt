package com.example.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.location.LocationTracker
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker,
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {

            state = state.copy(
                isLoading = true,
                error = null
            )

            locationTracker.getCurrentLocation()
                ?.let { location ->
                    val response = weatherRepository.getWeatherData(
                        latitude = location.latitude,
                        longitude = location.longitude
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
                } ?: kotlin.run {
                state = state.copy(
                    error = "Cannot retrieve location",
                    isLoading = false
                )
            }
        }
    }
}
