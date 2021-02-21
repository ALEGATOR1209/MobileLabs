package ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao

import androidx.room.*

@Dao
internal interface FilmDao {
    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmEntity>

    @Query("SELECT * FROM films WHERE imdb_id = :imdbID")
    suspend fun getFilm(imdbID: String): FilmEntity

    @Update
    suspend fun update(vararg films: FilmEntity)

    @Insert
    suspend fun insertAll(vararg films: FilmEntity)

    @Delete
    suspend fun delete(film: FilmEntity)

    @Query("DELETE FROM films WHERE imdb_id = :id")
    suspend fun deleteByImdbId(id: String)
}
