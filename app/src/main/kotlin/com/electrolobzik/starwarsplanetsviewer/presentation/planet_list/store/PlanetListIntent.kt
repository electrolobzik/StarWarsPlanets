package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store

import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

sealed interface PlanetListIntent {
    data object LoadPlanets : PlanetListIntent
    data class OpenPlanetDetails(val planet: Planet) : PlanetListIntent
}