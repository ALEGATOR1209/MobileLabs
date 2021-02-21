package ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.Image
import ua.kpi.comsys.ip8410.feature_gallery.core.domain.model.ImageCollection

@Entity(tableName = "images")
internal data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imageUrl: String,
)

internal fun ImageEntity.toImage() = Image(imageUrl)
internal fun <T: Iterable<ImageEntity>> T.toImages() = map(ImageEntity::toImage)
internal fun <T: Iterable<ImageEntity>> T.toImagesCollection() = ImageCollection(this.toImages())
