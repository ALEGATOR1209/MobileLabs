package ua.kpi.comsys.ip8410.feature_gallery.core.domain.repository

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection

internal interface ImageRepository {
    suspend fun getImages(request: String, count: Int): Result<ImageCollection, Exception>
}
