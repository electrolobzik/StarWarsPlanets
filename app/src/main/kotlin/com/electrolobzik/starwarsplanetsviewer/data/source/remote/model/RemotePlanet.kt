package com.electrolobzik.starwarsplanetsviewer.data.source.remote.model

import com.google.gson.annotations.SerializedName

/*
    Describes one planet on remote API
    {
        "name": "Tatooine",
        "rotation_period": "23",
        "orbital_period": "304",
        "diameter": "10465",
        "climate": "arid",
        "gravity": "1 standard",
        "terrain": "desert",
        "surface_water": "1",
        "population": "200000",
        "residents": [
            "https://swapi.dev/api/people/1/",
            "https://swapi.dev/api/people/2/",
            "https://swapi.dev/api/people/4/",
            "https://swapi.dev/api/people/6/",
            "https://swapi.dev/api/people/7/",
            "https://swapi.dev/api/people/8/",
            "https://swapi.dev/api/people/9/",
            "https://swapi.dev/api/people/11/",
            "https://swapi.dev/api/people/43/",
            "https://swapi.dev/api/people/62/"
        ],
        "films": [
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/4/",
            "https://swapi.dev/api/films/5/",
            "https://swapi.dev/api/films/6/"
        ],
        "created": "2014-12-09T13:50:49.641000Z",
        "edited": "2014-12-20T20:58:18.411000Z",
        "url": "https://swapi.dev/api/planets/1/"
    }
 */

data class RemotePlanet(
    @SerializedName("name")
    val name: String,

    @SerializedName("rotation_period")
    val rotationPeriod: String,

    @SerializedName("orbital_period")
    val orbitalPeriod: String,

    @SerializedName("diameter")
    val diameter: String,

    @SerializedName("climate")
    val climate: String,

    @SerializedName("gravity")
    val gravity: String,

    @SerializedName("terrain")
    val terrain: String,

    @SerializedName("surface_water")
    val surfaceWater: String,

    @SerializedName("population")
    val population: String
)

