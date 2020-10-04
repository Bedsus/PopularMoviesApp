package ru.bedsus.domain.repository

import io.reactivex.Single
import ru.bedsus.domain.model.Film

interface FilmApiRepository {

    fun getPopularFilmList(): Single<List<Film>>
}