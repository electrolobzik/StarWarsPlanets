package com.electrolobzik.starwarsplanetsviewer.di

import com.electrolobzik.starwarsplanetsviewer.data.repository.PlanetRepositoryImpl
import com.electrolobzik.starwarsplanetsviewer.domain.repository.PlanetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun providePlanetRepository(
        planetRepositoryImpl: PlanetRepositoryImpl
    ): PlanetRepository
}