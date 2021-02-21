package ua.kpi.comsys.ip8410.feature_films.data.datasource.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8410.feature_films.core.datasource.FilmDataSource
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao.FilmDao
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao.FilmEntity
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao.toFilm
import ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao.toFilmEntity

internal class FilmLocalDataSource(
    private val dao: FilmDao
) : FilmDataSource {

    override suspend fun getFilms(request: String): Result<List<Film>, Exception> = query {
        dao.getAllFilms().map(FilmEntity::toFilm).let { films ->
            val startsWith = films.filter { it.title.startsWith(request) }
            val onlyContains = films.filter {
                !it.title.startsWith(request) && it.title.contains(request)
            }

            startsWith + onlyContains
        }
    }

    override suspend fun getFilm(id: String): Result<Film, Exception> = query {
        dao.getFilm(id).toFilm()
    }

    override suspend fun addFilm(film: Film): Result<Unit, Exception> = query {
        dao.insertAll(film.toFilmEntity())
    }

    override suspend fun updateFilm(film: Film): Result<Unit, Exception> = query {
        val filmEntity = dao.getFilm(film.imdbID)
        val film2 = film.toFilmEntity().copy(uid = filmEntity.uid)
        dao.update(film2)
    }

    override suspend fun removeFilm(film: Film): Result<Unit, Exception> = query {
        dao.deleteByImdbId(film.imdbID)
    }

    private suspend fun <T> query(q: suspend () -> T) = try {
        Ok(q())
    } catch (e: Throwable) {
        Err(Exception(e.message))
    }
}
