package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    TopAppBar(title = {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onQueryChange.invoke(it)
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
    }, modifier = modifier)
}

@Preview
@Composable
private fun Preview() {
    CitySeekerTheme {
        TopBar(onQueryChange = {}, modifier = Modifier.fillMaxWidth())
    }
}