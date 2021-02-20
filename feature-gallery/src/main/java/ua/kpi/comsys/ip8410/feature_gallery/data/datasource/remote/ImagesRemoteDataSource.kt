package ua.kpi.comsys.ip8410.feature_gallery.data.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8410.feature_gallery.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.remote.api.PixabayApi
import ua.kpi.comsys.ip8410.feature_gallery.data.datasource.remote.api.provideApi

class ImagesRemoteDataSource(
    apiKey: String,
) : ImagesDataSource {
    @ExperimentalSerializationApi
    private val api: PixabayApi = provideApi(apiKey)

    override suspend fun getImages(
        request: String,
        count: Int
    ): Result<ImageCollection, Exception> {
        return try {
            Ok(api.getImages(request, count.toString()))
        } catch (e: Throwable) {
            Err(Exception(e.message))
        }
    }
}
