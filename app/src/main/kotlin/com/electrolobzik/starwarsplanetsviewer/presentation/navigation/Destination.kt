package com.electrolobzik.starwarsplanetsviewer.presentation.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.electrolobzik.starwarsplanetsviewer.core.utils.serializableType
import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface Destination {
    @Serializable
    data object PlanetList : Destination

    @Serializable
    data class PlanetDetails(val planet: Planet) : Destination {
        companion object {
            val typeMap = mapOf(typeOf<Planet>() to serializableType<Planet>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<PlanetDetails>(typeMap)
        }
    }
}
