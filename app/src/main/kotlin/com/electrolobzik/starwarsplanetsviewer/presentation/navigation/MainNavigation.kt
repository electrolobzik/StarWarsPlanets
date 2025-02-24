package com.electrolobzik.starwarsplanetsviewer.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.electrolobzik.starwarsplanetsviewer.R
import com.electrolobzik.starwarsplanetsviewer.core.mvi.getDescription
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_details.PlanetDetailsScreen
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.PlanetListScreen
import com.electrolobzik.starwarsplanetsviewer.presentation.theme.Dimens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    var upAvailable by remember { mutableStateOf(false) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { _ ->
            upAvailable = navController.previousBackStackEntry != null
        }
    }

    val screenTitle = remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val noConnectionErrorMessage = stringResource(R.string.no_connection)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = Dimens.toolbarIconWidth),
                        text = screenTitle.value,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier.size(Dimens.toolbarIconWidth)
                    ) {
                        if (upAvailable) {
                            IconButton(
                                onClick = {
                                    navController.navigateUp()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { contentPadding ->
        Navigation(
            modifier = modifier,
            navController = navController,
            screenTitle = screenTitle,
            coroutineScope = coroutineScope,
            snackbarHostState = snackbarHostState,
            noConnectionErrorMessage = noConnectionErrorMessage,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    contentPadding: PaddingValues,
    screenTitle: MutableState<String>,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    noConnectionErrorMessage: String
) {
    NavHost(
        modifier = modifier.padding(contentPadding),
        navController = navController,
        startDestination = Destination.PlanetList
    ) {
        composable<Destination.PlanetList> {
            screenTitle.value = stringResource(R.string.planet_list)

            PlanetListScreen(
                modifier = modifier,
                onPlanetSelected = { planet ->
                    navController.navigate(Destination.PlanetDetails(planet))
                },
                onNoConnection = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(noConnectionErrorMessage)
                    }
                },
                onUnknownError = { error ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(error.getDescription())
                    }
                }
            )
        }

        composable<Destination.PlanetDetails>(
            typeMap = Destination.PlanetDetails.typeMap
        ) { backStackEntry ->
            val planetDestination: Destination.PlanetDetails = backStackEntry.toRoute()
            screenTitle.value = planetDestination.planet.name

            PlanetDetailsScreen(modifier = modifier)
        }
    }
}