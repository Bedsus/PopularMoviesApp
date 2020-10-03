package ru.bedsus.data.repository

import io.reactivex.Single
import ru.bedsus.data.endpoint.FilmApiService
import ru.bedsus.data.mappers.FilmApiMapperImpl
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.repository.FilmRepository

class FilmRepositoryImpl(
    private val service: FilmApiService,
    private val mapper: FilmApiMapperImpl
) : FilmRepository {

    override fun getPopularFilmList(): Single<List<Film>> {
        return service.getPopularFilms("")
            .map { (it.results ?: listOf()).map(mapper::toModel) }
    }
}