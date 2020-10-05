package ru.bedsus.data.network.repository

import io.reactivex.Single
import ru.bedsus.data.network.endpoint.FilmApiService
import ru.bedsus.data.network.mappers.toModel
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.repository.FilmApiRepository

class FilmApiRepositoryImpl(
    private val service: FilmApiService
) : FilmApiRepository {

    /**
     * Получение списка популярных фильмов
     * 1. Получаем карту жанров кино
     * 2. Загружаем список фильмов
     * 3. Проводим маппинг данных. По идентификатору жанра кино из карты находим наименование.
     */
    override fun getPopularFilmList(): Single<List<Film>> {
        return getGenreMap().zipWith(service.getPopularFilms()) { genreMap, films ->
            (films.results ?: listOf()).map { it.toModel(genreMap) }
        }
    }

    /**
     * Получение карты жанров кино.
     * Ключ - идентификатор жанра, Значение - Наименование жанра
     */
    private fun getGenreMap(): Single<Map<Int, String>> {
        return service.getGenreList()
            .map { genreList ->
                (genreList.genres ?: listOf()).map { it.toModel() }
                    .map { it.id to it.name }
                    .toMap()
            }
    }
}