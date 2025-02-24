package com.electrolobzik.starwarsplanetsviewer.presentation.planet_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet
import com.electrolobzik.starwarsplanetsviewer.presentation.theme.Dimens
import com.electrolobzik.starwarsplanetsviewer.presentation.theme.StarWarsPlanetsViewerTheme
import com.electrolobzik.starwarsplanetsviewer.statics.SampleData


@Composable
fun PlanetDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PlanetDetailsViewModel = hiltViewModel()
) {
    val planet = viewModel.planet
    PlanetDetailsScreen(
        modifier = modifier,
        planet = planet
    )
}


@Composable
private fun PlanetDetailsScreen(
    modifier: Modifier = Modifier,
    planet: Planet
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.regularPadding),
        elevation = CardDefaults.cardElevation(Dimens.cardElevation)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.regularPadding)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.smallPadding),
                text = planet.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(text = "Climate: ${planet.climate}")
            Text(text = "Population: ${planet.population}")
            Text(text = "Diameter: ${planet.diameter}")
            Text(text = "Gravity: ${planet.gravity}")
            Text(text = "Terrain: ${planet.terrain}")
        }
    }
}

@Preview
@Composable
fun PlanetDetailsScreenPreview() {
    StarWarsPlanetsViewerTheme {
        PlanetDetailsScreen(
            planet = SampleData.planets.first()
        )
    }
}
