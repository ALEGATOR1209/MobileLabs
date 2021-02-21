package ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film

@Entity(
    tableName = "films",
    indices = [Index(value = ["imdb_id"], unique = true)]
)
internal data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,

    val title: String,
    val year: String,
    @ColumnInfo(name = "imdb_id")     val imdbID: String,
    @ColumnInfo(name = "imdb_rating") val imdbRating: String? = null,
    @ColumnInfo(name = "imdb_votes")  val imdbVotes: String? = null,
    val type: String,
    val poster: String,
    val rated: String? = null,
    val released: String? = null,
    val runtime: String? = null,
    val genre: String? = null,
    val director: String? = null,
    val writer: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val language: String? = null,
    val country: String? = null,
    val awards: String? = null,
    val production: String? = null,
)

internal fun FilmEntity.toFilm() = Film(
    title,
    year,
    imdbID,
    imdbRating,
    imdbVotes,
    type,
    poster,
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

internal fun Film.toFilmEntity() = FilmEntity(
    0,
    title,
    year,
    imdbID,
    imdbRating,
    imdbVotes,
    type,
    poster,
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
