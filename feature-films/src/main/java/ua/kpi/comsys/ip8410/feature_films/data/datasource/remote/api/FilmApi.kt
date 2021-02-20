package ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

internal interface FilmApi {
    @GET("/")
    suspend fun getFilms(@Query("s") request: String): FilmApiResponses

    @GET("/")
    suspend fun getFilm(@Query("i") id: String): FilmResponse
}
