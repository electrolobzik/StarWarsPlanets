package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store

import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

sealed interface PlanetListEffect {
    data object LoadingStarted: PlanetListEffect
    data object LoadingFinished : PlanetListEffect

    data class PlanetsLoaded(val planets: List<Planet>): PlanetListEffect
}