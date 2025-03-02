package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.electrolobzik.starwarsplanetsviewer.R
import com.electrolobzik.starwarsplanetsviewer.core.mvi.OneTimeEvent
import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store.PlanetListIntent
import com.electrolobzik.starwarsplanetsviewer.presentation.theme.Dimens
import com.electrolobzik.starwarsplanetsviewer.presentation.theme.StarWarsPlanetsViewerTheme
import com.electrolobzik.starwarsplanetsviewer.statics.SampleData

@Composable
fun PlanetListScreen(
    modifier: Modifier = Modifier,
    viewModel: PlanetListViewModel = hiltViewModel(),
    onPlanetSelected: (Planet) -> Unit,
    onNoConnection: () -> Unit,
    onUnknownError: (OneTimeEvent.UnknownError) -> Unit
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.navigationEventsFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect { event ->
                when (event) {
                    is PlanetListNavigationEvent.ShowPlanetDetails -> {
                        onPlanetSelected(event.planet)
                    }
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.oneTimeMessagesFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect { oneTimeMessage ->
                when (oneTimeMessage) {
                    OneTimeEvent.NoConnection -> onNoConnection()
                    is OneTimeEvent.UnknownError -> onUnknownError(oneTimeMessage)
                }
            }
    }

    PlanetListScreen(
        modifier = modifier,
        state = state,
        onPlanetSelected = { planet ->
            viewModel.acceptIntent(PlanetListIntent.OpenPlanetDetails(planet = planet))
        },
        onRetry = {
            viewModel.acceptIntent(PlanetListIntent.LoadPlanets)
        }
    )
}

@Composable
private fun PlanetListScreen(
    modifier: Modifier = Modifier,
    state: PlanetListViewState,
    onPlanetSelected: (Planet) -> Unit,
    onRetry: () -> Unit
) {
    when {
        state.isLoading -> {
            LoadingContent(modifier = modifier)
        }

        state.isEmpty -> {
            EmptyContent(
                modifier = modifier,
                onRetry = onRetry
            )
        }

        else -> {
            PlanetListContent(
                modifier = modifier,
                planets = state.planets,
                onPlanetSelected = onPlanetSelected
            )
        }
    }
}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(Dimens.progressIndicatorWidth),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun EmptyContent(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.empty_content),
            color = MaterialTheme.colorScheme.secondary
        )

        Button(
            modifier = Modifier.padding(top = Dimens.regularPadding),
            onClick = onRetry
        ) {
            Text(
                text = stringResource(R.string.retry)
            )
        }
    }
}

@Composable
private fun PlanetListContent(
    modifier: Modifier = Modifier,
    planets: List<Planet>,
    onPlanetSelected: (Planet) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding),
        contentPadding = PaddingValues(horizontal = Dimens.smallPadding, vertical = Dimens.smallPadding)
    ) {
        items(
            count = planets.size,
            key = { index -> planets[index].name }
        ) { index ->
            PlanetListItem(
                planet = planets[index],
                onPlanetSelected = { planet ->
                    onPlanetSelected(planet)
                }
            )
        }
    }
}

@Composable
private fun PlanetListItem(
    modifier: Modifier = Modifier,
    planet: Planet,
    onPlanetSelected: (Planet) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(Dimens.cardElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        onClick = {
            onPlanetSelected(planet)
        }
    ) {
        Column(
            modifier = Modifier.padding(Dimens.regularPadding)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = planet.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(text = "Climate: ${planet.climate}")
            Text(text = "Population: ${planet.population}")
        }
    }
}

@Preview
@Composable
fun PlanetListContentPreview() {
    StarWarsPlanetsViewerTheme {
        PlanetListContent(
            planets = SampleData.planets,
            onPlanetSelected = {}
        )
    }
}

@Preview
@Composable
fun PlanetListEmptyPreview() {
    StarWarsPlanetsViewerTheme {
        EmptyContent(
            onRetry = {}
        )
    }
}

@Preview
@Composable
fun PlanetListLoadingPreview() {
    StarWarsPlanetsViewerTheme {
        LoadingContent()
    }
}
