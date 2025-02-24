package com.electrolobzik.starwarsplanetsviewer.di

import com.electrolobzik.starwarsplanetsviewer.data.source.remote.ApiService
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.RemotePlanet
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.RemotePlanetListPage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object TestModule {
    
    @Provides
    @Singleton
    fun provideApiService(): ApiService = object : ApiService {
        override suspend fun getPlanetsPage(pageNumber: Int?): RemotePlanetListPage {
            return RemotePlanetListPage(
                itemCount = 1,
                nextPageUrl = null,
                previousPageUrl = null,
                planets = listOf(
                    RemotePlanet(
                        name = "Test Planet",
                        climate = "Test Climate",
                        population = "1000",
                        diameter = "10000",
                        terrain = "Test Terrain",
                        gravity = "1 standard"
                    )
                )
            )
        }
    }
} 