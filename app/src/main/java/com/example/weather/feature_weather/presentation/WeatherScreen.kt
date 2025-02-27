package com.example.weather.feature_weather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.core.presentation.ui.theme.DarkBlue
import com.example.weather.core.presentation.ui.theme.DeepBlue
import com.example.weather.feature_weather.presentation.components.ScreenHeader
import com.example.weather.feature_weather.presentation.components.WeatherCard
import com.example.weather.feature_weather.presentation.components.WeatherForecast

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navController: NavController,
) {
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
            .background(DarkBlue)
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

            WeatherCard(
                weatherInfo = viewModel.state.weatherInfo,
                backgroundColor = DeepBlue
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(state = viewModel.state)
        }

        if (viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
