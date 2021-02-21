package ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8410.feature_gallery.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.Image
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao.ImageDao
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao.ImageEntity
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao.toImagesCollection

internal class ImagesLocalDataSource(
    private val dao: ImageDao
) : ImagesDataSource {
    override suspend fun getImages(request: String, count: Int): Nothing = error("Not supported")

    override suspend fun getAllImages(): Result<ImageCollection, Exception> {
        return try {
            Ok(dao.getAllImages().toImagesCollection())
        } catch (e: Throwable) {
            Err(Exception(e.message))
        }
    }

    override suspend fun addImage(image: Image): Result<Unit, Exception> {
        return try {
            val entity = ImageEntity(0, image.largeImageURL)
            Ok(dao.addImages(entity))
        } catch (e: Throwable) {
            Err(Exception(e.message))
        }
    }
}
