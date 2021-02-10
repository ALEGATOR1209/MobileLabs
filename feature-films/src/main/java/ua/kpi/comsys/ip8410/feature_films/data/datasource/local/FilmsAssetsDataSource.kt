package ua.kpi.comsys.ip8410.feature_films.data.datasource.local

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8410.feature_films.core.datasource.FilmDataSource
import ua.kpi.comsys.ip8410.feature_films.core.domain.model.Film
import java.io.IOException
import java.nio.charset.Charset

internal class FilmsAssetsDataSource(
    private val assetManager: AssetManager,
    private val fileName: String
) : FilmDataSource {
    override fun getFilms(): List<Film> {
        val text = assetManager.open(fileName)
            .bufferedReader(Charset.defaultCharset())
            .readText()

        return Json.decodeFromString<FilmData>(text).data
    }

    override fun getPoster(film: Film): Drawable? {
        if (film.poster.isBlank()) return null
        return try {
            Drawable.createFromStream(
                assetManager.open(film.poster),
                null
            )
        } catch (e: IOException) {
            return null
        }
    }

    @Serializable
    private data class FilmData(@SerialName("Search") val data: List<Film>)
}
