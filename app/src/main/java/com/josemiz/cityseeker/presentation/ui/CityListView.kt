package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josemiz.cityseeker.presentation.model.Cities
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@Composable
fun CityListView(cities: Cities, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(cities.cities) {
            CityItem(city = it.city, country = it.country, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CitySeekerTheme {
        CityListView(
            cities = Cities(
                listOf(
                    City("Lima", "PE", 12.0, 12.0),
                    City("Los Angeles", "US", 21.0, 21.0),
                )
            )
        )
    }
}