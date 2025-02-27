package com.example.weather.feature_manage_cities.presentation.add_city.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.feature_manage_cities.domain.google.Prediction

@Composable
fun PredictionCard(
    prediction: Prediction,
    alreadyAdded: Boolean = false,
    modifier: Modifier = Modifier,
    onAddClick: (Prediction) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                text = prediction.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            if (alreadyAdded) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        text = "added",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic,
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        onAddClick(prediction)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = "add"
                    )
                }
            }
        }
    }
}
