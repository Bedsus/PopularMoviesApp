package ru.bedsus.data.room.repository

import io.reactivex.Maybe
import ru.bedsus.data.room.dao.FilmDao
import ru.bedsus.data.room.mapper.fromEntity
import ru.bedsus.data.room.mapper.toEntity
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.repository.FilmCacheRepository

class FilmCacheRepositoryImpl(
    private val dao: FilmDao
) : FilmCacheRepository {

    override fun getFilmInfoById(id: Int): Maybe<Film> {
        return dao.getById(id).map { it.fromEntity() }
    }

    override fun getPopularFilmList(): Maybe<List<Film>> {
        return dao.getAllFilms().map { films ->
            films.map { it.fromEntity() }
        }
    }

    override fun saveFilmListToCache(list: List<Film>) {
        dao.updateAllFilms(list.map {it.toEntity() })
    }
}