package ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
internal interface ImageDao {
    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<ImageEntity>

    @Insert
    suspend fun addImages(vararg imageEntity: ImageEntity)

    @Update
    suspend fun updateImage(vararg imageEntity: ImageEntity)
}
