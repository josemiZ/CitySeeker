package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@Composable
fun CityItem(
    city: String,
    country: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(onClick = onClick, modifier = modifier) {
        Text(
            text = "$city ,$country",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview
@Composable
private fun Preview(){
    CitySeekerTheme {
        CityItem(
            city = "Lima",
            country = "PE",
            modifier = Modifier.fillMaxWidth()
        ) {}
    }
}