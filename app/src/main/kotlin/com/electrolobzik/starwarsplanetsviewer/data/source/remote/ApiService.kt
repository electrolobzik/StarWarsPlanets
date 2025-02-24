package com.electrolobzik.starwarsplanetsviewer.data.source.remote

import com.electrolobzik.starwarsplanetsviewer.data.source.remote.RemotePaths.PAGE_PARAM
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.RemotePaths.PLANETS
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.RemotePlanetListPage
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(PLANETS)
    suspend fun getPlanetsPage(@Query(PAGE_PARAM) pageNumber: Int? = null): RemotePlanetListPage
}