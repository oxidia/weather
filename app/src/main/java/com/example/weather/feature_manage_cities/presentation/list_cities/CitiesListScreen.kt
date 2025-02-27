package com.example.weather.feature_manage_cities.presentation.list_cities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.core.util.Screen
import com.example.weather.core.util.UIEvent
import com.example.weather.feature_manage_cities.presentation.list_cities.components.CityCard
import com.example.weather.feature_manage_cities.presentation.list_cities.components.SearchButton

@Composable
fun CitiesListScreen(
    viewModel: CitiesListViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.OnNavigate -> {
                    navController.navigate(event.screen.route)
                }

                is UIEvent.PopBackStack -> {
                    navController.navigateUp()
                }

                else -> Unit
            }
        }
    }


    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(
                horizontal = 8.dp
            )
    ) {
        Text(
            text = "Manage cities",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchButton(
            onClick = {
                viewModel.onEvent(CitiesListEvent.OnSearchButtonClick)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.citiesList) { city ->
                CityCard(
                    city = city,
                    onDeleteClick = {
                        viewModel.onEvent(CitiesListEvent.OnDeleteCity(it))
                    },
                    onCardClick = {
                        navController.navigate(
                            Screen.Weather.passCoordinates(
                                lat =  city.latitude,
                                lng = city.longitude
                            )
                        )
                    }
                )
            }
        }
    }
}
