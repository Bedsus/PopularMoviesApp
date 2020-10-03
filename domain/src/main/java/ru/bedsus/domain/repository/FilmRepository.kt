package ru.bedsus.domain.repository

import io.reactivex.Single
import ru.bedsus.domain.model.Film

interface FilmRepository {

    fun getPopularFilmList(): Single<List<Film>>
}