package com.electrolobzik.starwarsplanetsviewer.data.repository

import com.electrolobzik.starwarsplanetsviewer.core.coroutines.DispatcherProvider
import com.electrolobzik.starwarsplanetsviewer.core.error_handling.runRequest
import com.electrolobzik.starwarsplanetsviewer.core.utils.ConnectionChecker
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.NetworkDataSource
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.toPlanet
import com.electrolobzik.starwarsplanetsviewer.domain.repository.PlanetRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository to work with Planets
 */
class PlanetRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val connectionChecker: ConnectionChecker,
    private val dispatchers: DispatcherProvider
) : PlanetRepository {

    override suspend fun getPlanets(pageNumber: Int) = runRequest(connectionChecker, "Loading planets") {
        withContext(dispatchers.io) {
            networkDataSource.getPlanetList(pageNumber = pageNumber)
                .map { remotePlanet ->
                    remotePlanet.toPlanet()
                }
        }
    }
}
