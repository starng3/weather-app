package com.example.weatherapp.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatherapp.R
import com.example.weatherapp.model.data.WeatherDisplayData
import com.example.weatherapp.view.theme.WeatherAppTheme
import java.math.BigDecimal

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherScreen(
    weatherDisplayData: MutableState<WeatherDisplayData?>,
    onSearchButtonClicked: (String) -> Unit
) {
    var userSearchInputText by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = userSearchInputText,
                onValueChange = { userSearchInputText = it },
                label = { Text(stringResource(R.string.search_label)) },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable { userSearchInputText = "" }
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(2f)
            )

            Button(
                onClick = { onSearchButtonClicked(userSearchInputText) },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.search))
            }
        }

        if (weatherDisplayData.value == null) return

        val currentWeatherDisplayData = weatherDisplayData.value

        currentWeatherDisplayData?.cityName?.let {
            Text(
                text = it,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        GlideImage(
            model = currentWeatherDisplayData?.weatherIconPath,
            contentDescription = currentWeatherDisplayData?.weatherIconCode
        )

        currentWeatherDisplayData?.currentTemp?.let {
            Text(
                text = "Current Temp: $it",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        currentWeatherDisplayData?.feelsLikeTemp?.let {
            Text(
                text = "Feels Like: $it",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun WeatherScreenPreview() {
    val weatherDisplayData = WeatherDisplayData(
        cityName = "San Francisco",
        currentTemp = BigDecimal("99"),
        feelsLikeTemp = BigDecimal("95"),
        weatherIconCode = "01d"
    )

    WeatherAppTheme {
        WeatherScreen(
            weatherDisplayData = remember { mutableStateOf(weatherDisplayData) },
            onSearchButtonClicked = {}
        )
    }
}