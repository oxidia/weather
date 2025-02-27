package com.example.weather.feature_manage_cities.presentation.list_cities.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.core.domain.model.City

@Composable
fun CityCard(
    city: City,
    modifier: Modifier = Modifier,
    onDeleteClick: (City) -> Unit,
    onCardClick: (City) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onCardClick(city)
            }
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
            .padding(horizontal = 8.dp)
            .then(modifier),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                text = city.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = {
                    onDeleteClick(city)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentDescription = "delete"
                )
            }
        }
    }
}
