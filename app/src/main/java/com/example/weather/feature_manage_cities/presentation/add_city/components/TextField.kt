package com.example.weather.feature_manage_cities.presentation.add_city.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,

    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(1000.dp)
    val fontSize = 12.sp

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(40.dp)
            .clip(shape)
            .background(
                color = MaterialTheme.colorScheme.background,
            )
            .padding(
                horizontal = 8.dp,
                vertical = 5.dp
            )
            .then(modifier)
    ) {
        if (leadingIcon != null) {
            leadingIcon()
        }

        Box {
            if (placeholder != null && value.isBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = fontSize
                    ),
                    text = placeholder,
                    color = Color.Gray,
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = fontSize,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.DarkGray
            )
            .padding(16.dp)
    ) {
        TextField(
            value = "",
            placeholder = "Enter location",
            onValueChange = {},
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "This is not empty",
            placeholder = "Enter location",
            onValueChange = {},
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            placeholder = "Enter location",
            onValueChange = {},
        )
    }
}
/**
placesList = listOf(
PlacesDto(
predictions = listOf(
Prediction(
description = "Khour",
matchedSubstrings = emptyList(),
placeId = "ChIJvRrAbPonpA0RJ0CBcCMt5IE",
reference = "ChIJvRrAbPonpA0RJ0CBcCMt5IE",
structuredFormatting = StructuredFormatting(
mainText = "",
secondaryText = "",
mainTextMatchedSubstrings = emptyList()
),
terms = emptyList(),
types = emptyList(),
)
),
status = "OK"
)
),
 */
