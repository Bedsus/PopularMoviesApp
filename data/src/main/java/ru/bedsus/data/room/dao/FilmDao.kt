package ru.bedsus.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Maybe
import ru.bedsus.data.room.model.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM popularFilm WHERE id = :id")
    fun getById(id: Int): Maybe<FilmEntity>

    @Query("SELECT * FROM popularFilm")
    fun getAllFilms(): Maybe<List<FilmEntity>>

    @Insert
    fun insertFilmList(list: List<FilmEntity>)

    @Query("DELETE FROM popularFilm")
    fun deleteAllFilms()

    @Transaction
    fun updateAllFilms(list: List<FilmEntity>) {
        deleteAllFilms()
        insertFilmList(list)
    }
}