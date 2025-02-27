package com.example.weather.feature_manage_cities.presentation.add_city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.core.util.UIEvent
import com.example.weather.feature_manage_cities.presentation.add_city.components.PredictionCard
import com.example.weather.feature_manage_cities.presentation.add_city.components.TextField

@Composable
fun AddCityScreen(
    navController: NavController,
    viewModel: AddCityViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = state.searchQuery,
                placeholder = "Enter location",
                onValueChange = { searchQuery ->
                    viewModel.onEvent(
                        AddCityEvent.OnSearchQueryChange(
                            searchQuery
                        )
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Cancel",
                color = Color.Blue,
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        viewModel.onEvent(AddCityEvent.OnCancelClick)
                    }
            )
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.suggestions) { prediction ->
                val cityAlreadyAdded =
                    state.citiesSet.contains(prediction.placeId)

                PredictionCard(
                    prediction = prediction,
                    alreadyAdded = cityAlreadyAdded,
                    onAddClick = {
                        viewModel.onEvent(
                            AddCityEvent.OnAddCityClick(
                                prediction = it
                            )
                        )
                    }
                )
            }
        }
    }
}
