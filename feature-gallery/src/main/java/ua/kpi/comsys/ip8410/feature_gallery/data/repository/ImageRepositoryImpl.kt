package ua.kpi.comsys.ip8410.feature_gallery.data.repository

import android.content.Context
import com.github.michaelbull.result.*
import ua.kpi.comsys.ip8410.feature_gallery.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.Image
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.repository.ImageRepository
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.ImagesLocalDataSource
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao.ImageDatabase
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.remote.ImagesRemoteDataSource

internal class ImageRepositoryImpl(
    private val local: ImagesDataSource,
    private val remote: ImagesDataSource,
) : ImageRepository {
    override suspend fun getImages(
        request: String,
        count: Int
    ): Result<ImageCollection, Exception> {
        return try {
            remote.getImages(request, count).mapBoth(
                success = { collection ->
                    collection.hits.forEach { image -> cacheImage(image) }
                    Ok(collection)
                },
                failure = {
                    local.getAllImages().andThen {
                        if (it.hits.size < count) Err(Exception("No photos"))
                        else Ok(it)
                    }
                }
            )
        } catch (e: Throwable) {
            Err(Exception(e.message))
        }
    }

    private suspend fun cacheImage(image: Image) {
        local.getAllImages().mapBoth(
            success = { collection ->
                val existing = collection.hits.find { it.largeImageURL == image.largeImageURL }
                if (existing == null) {
                    local.addImage(image).mapError {
                        println("Failed to cache images: $it")
                    }
                }
            },
            failure = {
                println("Failed to cache images: $it")
            }
        )
    }
}

internal fun repository(apiKey: String, context: Context) = ImageRepositoryImpl(
    ImagesLocalDataSource(
        ImageDatabase.getInstance(context).imageDao()
    ),
    ImagesRemoteDataSource(apiKey)
)
