package ua.kpi.comsys.ip8410.feature_films.data.repository

import android.content.Context
import com.github.michaelbull.result.*
import ua.kpi.comsys.ip8410.feature_films.core.datasource.FilmDataSource
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.core.domain.repository.FilmRepository
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.FilmLocalDataSource
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao.FilmDatabase
import ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.FilmRemoteDataSource

internal class FilmRepositoryImpl(
    private val local: FilmDataSource,
    private val remote: FilmDataSource,
) : FilmRepository {
    override suspend fun getFilm(imdbId: String): Result<Film, Exception> {
        return when (val res = remote.getFilm(imdbId)) {
            is Ok -> {
                val film = res.value
                local.getFilm(film.imdbID).mapBoth(
                    success = { local.updateFilm(film) },
                    failure = { local.addFilm(film) }
                ).mapError {
                    println("Failed to update film ${res.value.title}: ${it.message}")
                }
                res
            }
            is Err -> local.getFilm(imdbId)
        }
    }

    override suspend fun searchFilm(query: String): Result<List<Film>, Exception> {
        return when (val res = remote.getFilms(query)) {
            is Ok -> {
                res.value.forEach { film ->
                    local.getFilm(film.imdbID)
                        .onSuccess {
                            println("Film found: $it")
                        }
                        .onFailure {
                            local.addFilm(film)
                        }.mapError {
                            println("Failed to add film ${film.title}: ${it.message}")
                        }
                }
                res
            }

            is Err -> local.getFilms(query)
        }
    }
}

internal fun repository(apiKey: String, context: Context) = FilmRepositoryImpl(
    FilmLocalDataSource(FilmDatabase.getInstance(context).filmDao()),
    FilmRemoteDataSource(apiKey)
)
