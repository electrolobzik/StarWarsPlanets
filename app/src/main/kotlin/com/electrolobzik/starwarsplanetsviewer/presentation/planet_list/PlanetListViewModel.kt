package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electrolobzik.starwarsplanetsviewer.domain.repository.PlanetRepository
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store.PlanetListIntent
import com.electrolobzik.starwarsplanetsviewer.presentation.planet_list.store.PlanetListStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    planetRepository: PlanetRepository
) : ViewModel() {

    private val store = PlanetListStore(
        planetRepository = planetRepository,
        coroutineContext = viewModelScope.coroutineContext
    )

    init {
        store.acceptIntent(PlanetListIntent.LoadPlanets)
    }

    val stateFlow = store.stateFlow
    val oneTimeMessagesFlow = store.oneTimeMessagesFlow
    val navigationEventsFlow = store.navigationEventsFlow

    fun acceptIntent(intent: PlanetListIntent) = store.acceptIntent(intent)
}
