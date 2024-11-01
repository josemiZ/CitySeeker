package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josemiz.cityseeker.presentation.model.Cities
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@Composable
fun CitySeeker(
    cities: Cities,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    onFavoriteClicked: (City) -> Unit,
    onCityClick: (City) -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(onQueryChange = onQueryChange, modifier = Modifier.fillMaxWidth())
        },
        modifier = modifier
    ) { padding ->
        CityListView(
            cities = cities,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(10.dp),
            onFavoriteClicked = onFavoriteClicked,
            onItemClick = onCityClick
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CitySeekerTheme {
        CitySeeker(
            cities = Cities(
                listOf(
                    City(1,"Lima", "PE", true,12.0, 12.0),
                    City(2,"Los Angeles", "US", false,21.0, 21.0),
                )
            ),
            modifier = Modifier.fillMaxWidth(),
            onQueryChange = {},
            onFavoriteClicked = {}
        ){}
    }
}