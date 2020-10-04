package ru.bedsus.data.network.repository

import io.reactivex.Single
import ru.bedsus.data.network.endpoint.FilmApiService
import ru.bedsus.data.network.mappers.toModel
import ru.bedsus.data.resource.DataResource
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.repository.FilmApiRepository

class FilmApiRepositoryImpl(
    private val service: FilmApiService,
    private val resource: DataResource
) : FilmApiRepository {

    override fun getPopularFilmList(): Single<List<Film>> {
        return service.getGenreList(resource.apiKet).map { genreList ->
            (genreList.genres ?: listOf()).map { it.toModel() }
                .map { it.id to it.name }
                .toMap()
        }.zipWith(service.getPopularFilms(resource.apiKet)) { genreMap, films ->
            (films.results ?: listOf()).map { it.toModel(resource, genreMap) }
        }
    }
}