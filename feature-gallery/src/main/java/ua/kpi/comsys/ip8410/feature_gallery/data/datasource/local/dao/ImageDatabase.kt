package ua.kpi.comsys.ip8410.feature_gallery.data.datasource.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class], version = 1)
internal abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var instance: ImageDatabase? = null

        private const val DATABASE_NAME = "images_db"

        fun getInstance(context: Context): ImageDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): ImageDatabase = Room.databaseBuilder(
            context,
            ImageDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
