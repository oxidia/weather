package com.example.weather.feature_manage_cities.presentation.add_city

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.core.domain.model.City
import com.example.weather.core.domain.repository.CityRepository
import com.example.weather.core.util.UIEvent
import com.example.weather.feature_manage_cities.domain.google.Prediction
import com.example.weather.feature_manage_cities.domain.repository.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val cityRepository: CityRepository,
) : ViewModel() {

    var state by mutableStateOf(AddCityState())
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val searchQueryFlow = MutableStateFlow("")

    init {
        initCities()
        setupSearchQueryFlow()
    }

    fun onEvent(event: AddCityEvent) {
        when (event) {
            is AddCityEvent.OnCancelClick -> {
                sendUIEvent(UIEvent.PopBackStack)
            }

            is AddCityEvent.OnSearchQueryChange -> {
                viewModelScope.launch {
                    state = state.copy(
                        searchQuery = event.value
                    )

                    if (event.value.length > 2) {
                        searchQueryFlow.emit(event.value)
                    }
                }
            }

            is AddCityEvent.OnCitiesListChange -> {
                state = state.copy(
                    suggestions = event.values
                )
            }

            is AddCityEvent.OnAddCityClick -> {
                insertCity(event.prediction)
            }
        }
    }

    private fun initCities() {
        cityRepository.getCities()
            .onEach {
                val citiesSet = it.map { city -> city.placeId }
                    .toSet()

                state = state.copy(
                    citiesSet = citiesSet
                )
            }
            .launchIn(viewModelScope)
    }

    private fun insertCity(prediction: Prediction) {
        println("${prediction.placeId} ${prediction.description}")

        viewModelScope.launch {
            val place = placesRepository.getPlaceById(
                placeId = prediction.placeId
            )

            cityRepository.insertCity(
                City(
                    name = prediction.description,
                    placeId = prediction.placeId,
                    latitude = place.location!!.lat,
                    longitude = place.location.lng,
                )
            )
        }
    }

    private fun setupSearchQueryFlow() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .collect { searchQuery ->
                    val places =
                        placesRepository.getPredictions(searchQuery)
                    onEvent(
                        AddCityEvent.OnCitiesListChange(
                            values = places
                        )
                    )
                }
        }
    }

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
