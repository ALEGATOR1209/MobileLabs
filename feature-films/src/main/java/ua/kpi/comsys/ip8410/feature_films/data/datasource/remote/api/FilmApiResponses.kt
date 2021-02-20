package ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film

@Serializable
internal data class FilmApiResponses(
    @SerialName("Response")
    val response: String,

    @SerialName("Search")
    val films: List<Film>? = null,

    @SerialName("Error")
    val error: String? = null,

    val totalResults: String? = null
) {
    companion object {
        const val RESPONSE_TRUE = "True"
        const val RESPONSE_FALSE = "False"
    }
}

@Serializable
internal data class FilmResponse(
    @SerialName("Response")
    val response: String,

    @SerialName("Error")
    val error: String? = null,

    @SerialName("Title")
    val title: String? = null,

    @SerialName("Year")
    val year: String? = null,

    val imdbID: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null,

    @SerialName("Type")
    val type: String? = null,

    @SerialName("Poster")
    val poster: String? = null,

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
)

internal fun FilmResponse.toFilm() = Film(
    title!!,
    year!!,
    imdbID!!,
    imdbRating,
    imdbVotes,
    type!!,
    poster!!,
    rated,
    released,
    runtime,
    genre,
    director,
    writer,
    actors,
    plot,
    language,
    country,
    awards,
    production
)