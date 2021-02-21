package ua.kpi.comsys.ip8410.feature_gallery.core.datasource

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.Image
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection

internal interface ImagesDataSource {
    suspend fun getImages(request: String, count: Int): Result<ImageCollection, Exception>
    suspend fun getAllImages(): Result<ImageCollection, Exception>
    suspend fun addImage(image: Image): Result<Unit, Exception>
}
