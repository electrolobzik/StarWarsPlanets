package com.electrolobzik.starwarsplanetsviewer.domain.model

import kotlinx.serialization.Serializable

/**
 * Describes one planet
 */
@Serializable
data class Planet(
    val name: String,
    val climate: String,
    val population: String,
    val diameter: String,
    val gravity: String,
    val terrain: String
)