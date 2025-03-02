package com.example.weather.feature_weather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.feature_weather.presentation.components.ScreenHeader
import com.example.weather.feature_weather.presentation.components.WeatherCard
import com.example.weather.feature_weather.presentation.components.WeatherForecast

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state
//    val permissionLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestMultiplePermissions()
//    ) {result ->
//        val fineLocationGranted =
//            result[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
//        val coarseLocationGranted =
//            result[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
//
//        if (fineLocationGranted && coarseLocationGranted) {
//            viewModel.loadWeatherInfo()
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        permissionLauncher.launch(
//            arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//            )
//        )
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenHeader(
                onGoBack = {
                    navController.navigateUp()
                }
            )

            if (state.city != null && state.weatherInfo?.currentWeatherData != null) {
                WeatherCard(
                    weatherData = state.weatherInfo.currentWeatherData,
                    city = state.city,
                    backgroundColor = MaterialTheme.colorScheme.background
                )
            }
            Spacer(Modifier.height(16.dp))
            WeatherForecast(state = state)
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
