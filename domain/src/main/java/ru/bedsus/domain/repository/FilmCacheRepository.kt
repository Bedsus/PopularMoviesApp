package ru.bedsus.domain.repository

import io.reactivex.Maybe
import ru.bedsus.domain.model.Film

interface FilmCacheRepository {

    fun getFilmInfoById(id: Int): Maybe<Film>

    fun getPopularFilmList(): Maybe<List<Film>>

    fun saveFilmListToCache(list:List<Film>)
}