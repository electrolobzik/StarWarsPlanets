package com.electrolobzik.starwarsplanetsviewer.data.source.remote

import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.RemotePlanet
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPlanetList(pageNumber: Int = 1): List<RemotePlanet> {
        return apiService.getPlanetsPage(pageNumber = pageNumber).planets
    }
}