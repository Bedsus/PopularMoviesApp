package ru.bedsus.domain.repository

import io.reactivex.Maybe
import ru.bedsus.domain.model.Film

interface FilmCacheRepository {

    fun getPopularFilmList(): Maybe<List<Film>>

    fun saveFilmListToCache(list:List<Film>)
}