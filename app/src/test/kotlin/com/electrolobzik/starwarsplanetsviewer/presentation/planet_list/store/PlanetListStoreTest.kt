package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store

import com.electrolobzik.starwarsplanetsviewer.core.error_handling.DataResult
import com.electrolobzik.starwarsplanetsviewer.core.mvi.OneTimeEvent
import com.electrolobzik.starwarsplanetsviewer.domain.repository.PlanetRepository
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.PlanetListViewState
import com.electrolobzik.starwarsplanetsviewer.statics.SampleData
import com.electrolobzik.starwarsplanetsviewer.utils.TestDispatcherProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlanetListStoreTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testDispatcherProvider = TestDispatcherProvider(testDispatcher)

    private val planetRepository = mock<PlanetRepository>()

    private lateinit var planetListStore: PlanetListStore

    @Before
    fun setup() {
        planetListStore = PlanetListStore(
            planetRepository = planetRepository,
            dispatchers = testDispatcherProvider
        )
    }

    @Test
    fun `initial state`() = runTest(testDispatcher) {
        // Assert
        val expectedState = PlanetListViewState(isLoading = false, planets = emptyList())
        assertEquals(expectedState, planetListStore.stateFlow.value)
    }

    @Test
    fun loadData() = runTest(testDispatcher) {
        // Given
        val planets = SampleData.planets
        val planetsDataResult = DataResult.Success(planets, "Loading planets")
        whenever(planetRepository.getPlanets(Mockito.anyInt())).doReturn(planetsDataResult)

        // When
        planetListStore.acceptIntent(PlanetListIntent.LoadPlanets)
        withTimeout(1000) {
            planetListStore.stateFlow.first { !it.isLoading }
        }
        testScheduler.advanceUntilIdle()

        // Then
        val expectedState = PlanetListViewState(isLoading = false, planets = planets)
        assertEquals(expectedState, planetListStore.stateFlow.value)
        verify(planetRepository).getPlanets(Mockito.anyInt())
    }

    @Test
    fun `unknown error on loading`() = runTest(testDispatcher) {
        // Given
        whenever(planetRepository.getPlanets(Mockito.anyInt())).doReturn(DataResult.UnknownError(IllegalStateException(), ""))

        // When
        planetListStore.acceptIntent(PlanetListIntent.LoadPlanets)
        val oneTimeEvent = withTimeout(1000) {
            planetListStore.oneTimeMessagesFlow.first()
        }
        withTimeout(1000) {
            planetListStore.stateFlow.first { !it.isLoading }
        }
        testScheduler.advanceUntilIdle()

        // Then
        assertEquals(false, planetListStore.stateFlow.value.isLoading)
        assertEquals(OneTimeEvent.UnknownError::class, oneTimeEvent::class)
        verify(planetRepository).getPlanets(Mockito.anyInt())
    }
}