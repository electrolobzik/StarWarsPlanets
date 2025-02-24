package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list

import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

sealed interface PlanetListNavigationEvent {
    data class ShowPlanetDetails(val planet: Planet) : PlanetListNavigationEvent
}