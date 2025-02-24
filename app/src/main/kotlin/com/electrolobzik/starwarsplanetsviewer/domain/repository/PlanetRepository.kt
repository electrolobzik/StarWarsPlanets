package com.electrolobzik.starwarsplanetsviewer.domain.repository

import com.electrolobzik.starwarsplanetsviewer.core.error_handling.DataResult
import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

/**
 * Repository to work with Planets
 */
interface PlanetRepository {
    suspend fun getPlanets(pageNumber: Int): DataResult<List<Planet>>
}