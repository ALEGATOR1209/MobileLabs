package ua.kpi.comsys.ip8410.feature_gallery.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Image(
    val largeImageURL: String
)

@Serializable
internal data class ImageCollection(
    val hits: List<Image>
)