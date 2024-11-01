package com.josemiz.cityseeker.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme

@Composable
fun CityItem(
    city: City,
    modifier: Modifier = Modifier,
    onFavoriteClicked: () -> Unit,
    onClick: () -> Unit
) {
    Card(onClick = onClick, modifier = modifier) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (title, subtitle, favorite) = createRefs()
            val icon = if(city.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

            Text(
                text = city.getName(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(favorite.start)
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = city.getCoordinates(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(title.bottom, margin = 5.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    width = Dimension.fillToConstraints
                }
            )
            IconButton(
                onClick = onFavoriteClicked,
                modifier = Modifier.constrainAs(favorite) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(title.end, margin = 5.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                }) {
                Icon(imageVector = icon, contentDescription = city.getContentDescription())
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CitySeekerTheme {
        CityItem(
            city = City(1, "Lima", "PE", false, -123.2, 1234.2),
            modifier = Modifier.fillMaxWidth(),
            onFavoriteClicked = {}
        ) {}
    }
}