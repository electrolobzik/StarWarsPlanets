package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list

import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

data class PlanetListViewState(
    val planets: List<Planet> = emptyList(),
    val isLoading: Boolean = false,
    val lastPage: Int? = null
) {
    val isEmpty get() = planets.isEmpty()
}
