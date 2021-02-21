package ua.kpi.comsys.ip8410.feature_films.data.datasource.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmEntity::class], version = 1)
internal abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var instance: FilmDatabase? = null

        private const val DATABASE_NAME = "films_db"

        fun getInstance(context: Context): FilmDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): FilmDatabase = Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}