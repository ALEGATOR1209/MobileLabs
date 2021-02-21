package ua.kpi.comsys.ip8410.feature_films.data.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8410.feature_films.core.datasource.FilmDataSource
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api.FilmApi
import ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api.FilmApiResponses
import ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api.provideApi
import ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api.toFilm

internal class FilmRemoteDataSource(
    apiKey: String,
) : FilmDataSource {
    @ExperimentalSerializationApi
    private val api = provideApi<FilmApi>(apiKey)

    override suspend fun getFilms(request: String): Result<List<Film>, Exception> {
        try {
            val response = api.getFilms(request)

            if (response.response != FilmApiResponses.RESPONSE_TRUE) {
                return Err(Exception(response.error ?: "Unknown exception"))
            }

            if (response.films == null) {
                return Err(Exception("No films"))
            }

            return Ok(response.films)
        } catch (e: Throwable) {
            return Err(Exception(e.message))
        }
    }

    override suspend fun getFilm(id: String): Result<Film, Exception> {
        try {
            val response = api.getFilm(id)

            if (response.response != FilmApiResponses.RESPONSE_TRUE) {
                return Err(Exception(response.error ?: "Unknown exception"))
            }

            return Ok(response.toFilm())
        } catch (e: Throwable) {
            return Err(Exception(e.message))
        }
    }

    override suspend fun addFilm(film: Film): Nothing = error("Not supported")
    override suspend fun updateFilm(film: Film): Nothing = error("Not supported")
    override suspend fun removeFilm(film: Film): Nothing = error("Not supported")
}
