package com.example.weatherapp.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.model.data.SearchResult
import com.example.weatherapp.view.theme.WeatherAppTheme
import java.math.BigDecimal

@Composable
fun SearchResultListItem(
    data: SearchResult,
    modifier: Modifier = Modifier
) {
    Row(modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val displayString = StringBuilder(data.city)
            if (data.state != null) {
                displayString.append(", " + data.state)
            }
            displayString.append(", " + data.country)
            Text(
                text = displayString.toString(),
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchResultListItemPreview() {
    val searchResult =
        SearchResult(
            city = "San Francisco",
            state = "California",
            country = "US",
            latitude = BigDecimal("1"),
            longitude = BigDecimal("1")
        )

    WeatherAppTheme {
        SearchResultListItem(searchResult)
    }
}

