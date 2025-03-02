package com.electrolobzik.starwarsplanetsviewer.data

import com.electrolobzik.starwarsplanetsviewer.core.error_handling.DataResult
import com.electrolobzik.starwarsplanetsviewer.core.utils.ConnectionChecker
import com.electrolobzik.starwarsplanetsviewer.data.repository.PlanetRepositoryImpl
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.NetworkDataSource
import com.electrolobzik.starwarsplanetsviewer.statics.SampleData
import com.electrolobzik.starwarsplanetsviewer.utils.TestDispatcherProvider
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PlanetRepositoryImplTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testDispatcherProvider = TestDispatcherProvider(testDispatcher)

    private lateinit var networkDataSource: NetworkDataSource
    private lateinit var connectionChecker: ConnectionChecker
    private lateinit var planetRepository: PlanetRepositoryImpl

    @Before
    fun setUp() {
        networkDataSource = mock()
        connectionChecker = mock()
        planetRepository = PlanetRepositoryImpl(
            networkDataSource = networkDataSource,
            connectionChecker = connectionChecker,
            dispatchers = testDispatcherProvider
        )
    }

    @Test
    fun `getPlanets should return mapped Planet list`() = runTest(testDispatcher) {
        // Given
        val pageNumber = 1
        val expectedPlanets = SampleData.planets
        val expectedResult = DataResult.Success(expectedPlanets, "Loading planets")

        whenever(connectionChecker.isNetworkAvailable()).thenReturn(true)
        whenever(networkDataSource.getPlanetList(pageNumber)).thenReturn(SampleData.remotePlanets)

        // When
        val actualPlanetsResult = planetRepository.getPlanets(pageNumber)

        // Then
        assertEquals(expectedResult, actualPlanetsResult)
        val actualPlanets = (actualPlanetsResult as DataResult.Success).data
        assertEquals(expectedPlanets.size, actualPlanets.size)
        for (i in expectedPlanets.indices) {
            assertEquals(expectedPlanets[i], actualPlanets[i])
        }
    }
}