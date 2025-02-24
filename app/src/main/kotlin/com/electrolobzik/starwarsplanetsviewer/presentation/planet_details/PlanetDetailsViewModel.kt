package com.electrolobzik.starwarsplanetsviewer.presentation.planet_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.electrolobzik.starwarsplanetsviewer.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val planet = Destination.PlanetDetails.from(savedStateHandle).planet
}
