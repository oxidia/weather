package com.example.weather.feature_manage_cities.presentation.list_cities.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyListPlaceholder(
    modifier: Modifier = Modifier,
    onAddNewCityClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Cities list is empty",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onAddNewCityClick
        ) {
            Text(
                text = "Add new city?",
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
