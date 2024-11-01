package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josemiz.cityseeker.presentation.model.Cities
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@Composable
fun CityListView(
    cities: Cities,
    modifier: Modifier = Modifier,
    onFavoriteClicked: (City) -> Unit,
    onItemClick: (City) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        items(cities.cities) {
            CityItem(
                city = it,
                modifier = Modifier.fillMaxWidth(),
                onFavoriteClicked = { onFavoriteClicked.invoke(it) },
                onClick = { onItemClick.invoke(it) }
            )
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
                    City(1,"Lima", "PE", true,12.0, 12.0),
                    City(2,"Los Angeles", "US", false,21.0, 21.0),
                )
            ),
            onFavoriteClicked = {}
        ){}
    }
}