package ua.kpi.comsys.ip8410.feature_films.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Film(
    @SerialName("Title")
    val title: String,

    @SerialName("Year")
    val year: String,

    val imdbID: String,

    @SerialName("Type")
    val type: String,

    @SerialName("Poster")
    val poster: String
)
