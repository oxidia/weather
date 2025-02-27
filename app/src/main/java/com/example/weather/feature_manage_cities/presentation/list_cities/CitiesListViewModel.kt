package com.example.weather.feature_manage_cities.presentation.list_cities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.core.domain.repository.CityRepository
import com.example.weather.core.util.Screen
import com.example.weather.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesListViewModel @Inject constructor(
    private val cityRepository: CityRepository,
) : ViewModel() {

    var state by mutableStateOf(CitiesListState())
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        initCities()
    }

    fun onEvent(event: CitiesListEvent) {
        when (event) {
            is CitiesListEvent.OnSearchButtonClick -> {
                sendUIEvent(UIEvent.OnNavigate(Screen.AddCity))
            }

            is CitiesListEvent.PopBackStack -> {
                sendUIEvent(UIEvent.PopBackStack)
            }

            is CitiesListEvent.OnDeleteCity -> {
                viewModelScope.launch {
                    cityRepository.deleteCity(event.city)
                }
            }
        }
    }

    private fun initCities() {
        cityRepository.getCities()
            .onEach {
                state = state.copy(
                    citiesList = it
                )
            }.launchIn(viewModelScope)
    }

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
