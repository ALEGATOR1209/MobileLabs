package ua.kpi.comsys.ip8410.feature_films.core.datasource

import android.graphics.drawable.Drawable
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film

internal interface FilmDataSource {
    fun getFilms(): List<Film>
    fun getPoster(film: Film): Drawable?
    fun getFilm(id: String): Film?
    fun addFilm(film: Film)
    fun removeFilm(film: Film)
}
