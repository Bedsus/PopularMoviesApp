package ru.bedsus.data.mappers

import ru.bedsus.data.models.FilmApi
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.repository.FilmModelMapper

class FilmApiMapperImpl : FilmModelMapper<FilmApi, Film> {
    override fun toModel(from: FilmApi): Film {
        with(from) {
            return Film(
                id = id ?: 0,
                title = title ?: "",
                releaseDate = releaseDate ?: "",
                popularity = popularity ?: 0f,
                voteCount = voteCount ?: 0,
                posterPath = IMAGE_API_URL + (posterPath ?: ""),
                backdropPath = backdropPath ?: "",
                voteAverage = voteAverage ?: 0f,
                overview = overview ?: ""
            )
        }
    }
    companion object {
        const val IMAGE_API_URL = "https://image.tmdb.org/t/p/w500"
    }
}