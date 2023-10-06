package com.example.weatherapp.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.model.data.SearchResult
import com.example.weatherapp.view.components.SearchResultListItem
import com.example.weatherapp.view.theme.WeatherAppTheme
import java.math.BigDecimal

/**
 * If had more time, would implement loading
 */
@Composable
fun SearchResultsScreen(
    searchResults: SnapshotStateList<SearchResult>,
    onItemClicked: (SearchResult) -> Unit
) {
    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(searchResults) { index, item ->
            SearchResultListItem(
                data = item,
                modifier = Modifier.clickable {
                    onItemClicked(item)
                }
            )
            if (index != searchResults.size - 1) {
                Divider()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchResultsScreenPreview() {
    val searchResultList = listOf(
        SearchResult(
            city = "San Francisco",
            state = "California",
            country = "US",
            latitude = BigDecimal("1"),
            longitude = BigDecimal("1")
        )
    )

    WeatherAppTheme {
        SearchResultsScreen(
            searchResults = searchResultList.toMutableStateList(),
            onItemClicked = {}
        )
    }
}