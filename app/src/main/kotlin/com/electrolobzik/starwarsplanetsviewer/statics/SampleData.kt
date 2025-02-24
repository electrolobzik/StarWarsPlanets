package com.electrolobzik.starwarsplanetsviewer.statics

import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.RemotePlanet
import com.electrolobzik.starwarsplanetsviewer.domain.model.Planet

object SampleData {
    val remotePlanets = listOf(
        RemotePlanet(
            name = "Tatooine",
            rotationPeriod = "23",
            orbitalPeriod = "304",
            diameter = "10465",
            climate = "arid",
            gravity = "1 standard",
            terrain = "desert",
            surfaceWater = "1",
            population = "200000"
        ),
        RemotePlanet(
            name = "Alderaan",
            rotationPeriod = "24",
            orbitalPeriod = "364",
            diameter = "12500",
            climate = "temperate",
            gravity = "1 standard",
            terrain = "grasslands, mountains",
            surfaceWater = "40",
            population = "2000000000"
        ),
        RemotePlanet(
            name = "Yavin IV",
            rotationPeriod = "24",
            orbitalPeriod = "4818",
            diameter = "10200",
            climate = "temperate, tropical",
            gravity = "1 standard",
            terrain = "jungle, rainforests",
            surfaceWater = "8",
            population = "1000"
        ),
        RemotePlanet(
            name = "Hoth",
            rotationPeriod = "23",
            orbitalPeriod = "549",
            diameter = "7200",
            climate = "frozen",
            gravity = "1.1 standard",
            terrain = "tundra, ice caves, mountain ranges",
            surfaceWater = "100",
            population = "unknown"
        ),
        RemotePlanet(
            name = "Dagobah",
            rotationPeriod = "23",
            orbitalPeriod = "341",
            diameter = "8900",
            climate = "murky",
            gravity = "N/A",
            terrain = "swamp, jungles",
            surfaceWater = "8",
            population = "unknown"
        ),
        RemotePlanet(
            name = "Bespin",
            rotationPeriod = "12",
            orbitalPeriod = "5110",
            diameter = "118000",
            climate = "temperate",
            gravity = "1.5 (surface), 1 standard (Cloud City)",
            terrain = "gas giant",
            surfaceWater = "0",
            population = "6000000"
        ),
        RemotePlanet(
            name = "Endor",
            rotationPeriod = "18",
            orbitalPeriod = "402",
            diameter = "4900",
            climate = "temperate",
            gravity = "0.85 standard",
            terrain = "forests, mountains, lakes",
            surfaceWater = "8",
            population = "30000000"
        ),
        RemotePlanet(
            name = "Naboo",
            rotationPeriod = "26",
            orbitalPeriod = "312",
            diameter = "12120",
            climate = "temperate",
            gravity = "1 standard",
            terrain = "grassy hills, swamps, forests, mountains",
            surfaceWater = "12",
            population = "4500000000"
        ),
        RemotePlanet(
            name = "Coruscant",
            rotationPeriod = "24",
            orbitalPeriod = "368",
            diameter = "12240",
            climate = "temperate",
            gravity = "1 standard",
            terrain = "cityscape, mountains",
            surfaceWater = "unknown",
            population = "1000000000000"
        ),
        RemotePlanet(
            name = "Kamino",
            rotationPeriod = "27",
            orbitalPeriod = "463",
            diameter = "19720",
            climate = "temperate",
            gravity = "1 standard",
            terrain = "ocean",
            surfaceWater = "100",
            population = "1000000000"
        )
    )

    val planets = listOf(
        Planet(
            name = "Tatooine",
            climate = "arid",
            population = "200000",
            diameter = "10465",
            gravity = "1 standard",
            terrain = "desert"
        ),
        Planet(
            name = "Alderaan",
            climate = "temperate",
            population = "2000000000",
            diameter = "12500",
            gravity = "1 standard",
            terrain = "grasslands, mountains"
        ),
        Planet(
            name = "Yavin IV",
            climate = "temperate, tropical",
            population = "1000",
            diameter = "10200",
            gravity = "1 standard",
            terrain = "jungle, rainforests"
        ),
        Planet(
            name = "Hoth",
            climate = "frozen",
            population = "unknown",
            diameter = "7200",
            gravity = "1.1 standard",
            terrain = "tundra, ice caves, mountain ranges"
        ),
        Planet(
            name = "Dagobah",
            climate = "murky",
            population = "unknown",
            diameter = "8900",
            gravity = "unknown",
            terrain = "swamp, jungles"
        ),
        Planet(
            name = "Bespin",
            climate = "temperate",
            population = "6000000",
            diameter = "118000",
            gravity = "1.5 (surface), 1 standard (Cloud City)",
            terrain = "gas giant"
        ),
        Planet(
            name = "Endor",
            climate = "temperate",
            population = "30000000",
            diameter = "4900",
            gravity = "0.85 standard",
            terrain = "forests, mountains, lakes"
        ),
        Planet(
            name = "Naboo",
            climate = "temperate",
            population = "4500000000",
            diameter = "12120",
            gravity = "1 standard",
            terrain = "grassy hills, swamps, forests, mountains"
        ),
        Planet(
            name = "Coruscant",
            climate = "temperate",
            population = "1000000000000",
            diameter = "12240",
            gravity = "1 standard",
            terrain = "cityscape, mountains"
        ),
        Planet(
            name = "Kamino",
            climate = "temperate",
            population = "1000000000",
            diameter = "19720",
            gravity = "1 standard",
            terrain = "ocean"
        )
    )
}