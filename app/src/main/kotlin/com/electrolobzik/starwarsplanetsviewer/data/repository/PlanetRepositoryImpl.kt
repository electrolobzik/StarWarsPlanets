package com.electrolobzik.starwarsplanetsviewer.data.repository

import com.electrolobzik.starwarsplanetsviewer.core.error_handling.runRequest
import com.electrolobzik.starwarsplanetsviewer.core.utils.ConnectionChecker
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.NetworkDataSource
import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet
import com.electrolobzik.starwarsplanetsviewer.domain.repository.PlanetRepository
import javax.inject.Inject

/**
 * Repository to work with Planets
 */
class PlanetRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val connectionChecker: ConnectionChecker
) : PlanetRepository {

    override suspend fun getPlanets(pageNumber: Int) = runRequest(connectionChecker, "Loading planets") {
        networkDataSource.getPlanetList(pageNumber = pageNumber)
            .map { remotePlanet ->
                Planet(
                    name = remotePlanet.name,
                    climate = remotePlanet.climate,
                    population = remotePlanet.population,
                    diameter = remotePlanet.diameter,
                    terrain = remotePlanet.terrain,
                    gravity = if (remotePlanet.gravity == "N/A") "unknown" else remotePlanet.gravity    // We have to override gravity because some planets have gravity "N/A" and it breaks the typesafe args in navigation (it uses regex inside)
                )
            }
    }
}
