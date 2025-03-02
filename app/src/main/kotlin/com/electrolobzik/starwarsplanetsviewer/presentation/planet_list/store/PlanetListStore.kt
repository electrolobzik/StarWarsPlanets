package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store

import com.electrolobzik.starwarsplanetsviewer.core.coroutines.DispatcherProvider
import com.electrolobzik.starwarsplanetsviewer.core.error_handling.DataResult
import com.electrolobzik.starwarsplanetsviewer.core.mvi.BaseStore
import com.electrolobzik.starwarsplanetsviewer.core.mvi.OneTimeEvent
import com.electrolobzik.starwarsplanetsviewer.domain.repository.PlanetRepository
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.PlanetListNavigationEvent
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.PlanetListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlanetListStore(
    private val planetRepository: PlanetRepository,
    dispatchers: DispatcherProvider,
) : BaseStore<PlanetListIntent, PlanetListEffect, PlanetListViewState, OneTimeEvent, PlanetListNavigationEvent>(defaultDispatcher = dispatchers.default) {

    override val state = MutableStateFlow(PlanetListViewState())

    override fun acceptIntent(intent: PlanetListIntent) {
        when (intent) {
            PlanetListIntent.LoadPlanets -> loadPlanets()

            is PlanetListIntent.OpenPlanetDetails -> emitNavigationEvent(
                PlanetListNavigationEvent.ShowPlanetDetails(intent.planet)
            )
        }
    }

    override fun reduce(state: PlanetListViewState, effect: PlanetListEffect): PlanetListViewState {
        return when (effect) {
            PlanetListEffect.LoadingStarted -> state.copy(isLoading = true)
            PlanetListEffect.LoadingFinished -> state.copy(isLoading = false)
            is PlanetListEffect.PlanetsLoaded -> state.copy(isLoading = false, planets = effect.planets)
        }
    }

    private fun loadPlanets() {
        emitEffect(PlanetListEffect.LoadingStarted)
        launch {
            val result = planetRepository.getPlanets(state.value.lastPage ?: FIRST_PAGE)
            when (result) {
                is DataResult.Success -> emitEffect(PlanetListEffect.PlanetsLoaded(result.data))
                is DataResult.ConnectionIssue -> {
                    emitEffect(PlanetListEffect.LoadingFinished)
                    emitOneTimeMessage(OneTimeEvent.NoConnection)
                }

                is DataResult.UnknownError -> {
                    emitEffect(PlanetListEffect.LoadingFinished)
                    emitOneTimeMessage(OneTimeEvent.UnknownError(result.unknownError))
                }
            }
        }
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}