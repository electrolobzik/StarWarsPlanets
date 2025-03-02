package com.electrolobzik.starwarsplanetsviewer.data.source.remote.model

import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

fun RemotePlanet.toPlanet(): Planet {
    return Planet(
        name = name,
        climate = climate,
        population = population,
        diameter = diameter,
        terrain = terrain,
        gravity = if (gravity == "N/A") "unknown" else gravity    // We have to override gravity because some planets have gravity "N/A" and it breaks the typesafe args in navigation (it uses regex inside)
    )
}