package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josemiz.cityseeker.presentation.model.Cities
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@Composable
fun CitySeeker(cities: Cities, modifier: Modifier = Modifier, onCityClick: (City) -> Unit) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(5.dp).fillMaxWidth().padding(5.dp)
            )
        },
        modifier = modifier
    ) { padding ->
        CityListView(
            cities = cities,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(10.dp),
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
                    City("Lima", "PE", 12.0, 12.0),
                    City("Los Angeles", "US", 21.0, 21.0),
                )
            ),
            modifier = Modifier.fillMaxWidth()
        ){}
    }
}