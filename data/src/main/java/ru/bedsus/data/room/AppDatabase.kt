package ru.bedsus.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bedsus.data.room.dao.FilmDao
import ru.bedsus.data.room.model.FilmEntity

@Database(entities = [FilmEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
}