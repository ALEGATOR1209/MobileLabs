package ua.kpi.comsys.ip8410.feature_films.core.domain.repository

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film

internal interface FilmRepository {
    suspend fun getFilm(imdbId: String): Result<Film, Exception>
    suspend fun searchFilm(query: String): Result<List<Film>, Exception>
}
