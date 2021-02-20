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
    val imdbRating: String? = null,
    val imdbVotes: String? = null,

    @SerialName("Type")
    val type: String,

    @SerialName("Poster")
    val poster: String,

    @SerialName("Rated")
    val rated: String? = null,

    @SerialName("Released")
    val released: String? = null,

    @SerialName("Runtime")
    val runtime: String? = null,

    @SerialName("Genre")
    val genre: String? = null,

    @SerialName("Director")
    val director: String? = null,

    @SerialName("Writer")
    val writer: String? = null,

    @SerialName("Actors")
    val actors: String? = null,

    @SerialName("Plot")
    val plot: String? = null,

    @SerialName("Language")
    val language: String? = null,

    @SerialName("Country")
    val country: String? = null,

    @SerialName("Awards")
    val awards: String? = null,

    @SerialName("Production")
    val production: String? = null,
) {
    companion object {
        const val NO_POSTER = "N/A"
    }
}
