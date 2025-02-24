package com.electrolobzik.starwarsplanetsviewer.data.source.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Represents one page of API response for the planets list
 *
 *  {
 *     "count": 60,
 *     "next": "https://swapi.dev/api/planets/?page=2",
 *     "previous": null,
 *     "results": [...]
 *  }
 */
data class RemotePlanetListPage(
    @SerializedName("count")
    val itemCount: Int,

    @SerializedName("next")
    val nextPageUrl: String?,

    @SerializedName("previous")
    val previousPageUrl: String?,

    @SerializedName("results")
    val planets: List<RemotePlanet>
)