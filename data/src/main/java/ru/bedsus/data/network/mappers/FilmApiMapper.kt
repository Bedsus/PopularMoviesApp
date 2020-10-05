package ru.bedsus.data.network.mappers

import ru.bedsus.data.network.models.FilmApi
import ru.bedsus.domain.model.Film

private const val IMAGE_API_URL = "https://image.tmdb.org/t/p/w500"

internal fun FilmApi.toModel(genreMap: Map<Int, String>): Film {
    return Film(
        id = id ?: 0,
        title = title ?: "",
        releaseDate = releaseDate ?: "",
        popularity = popularity ?: 0f,
        voteCount = voteCount ?: 0,
        posterPath = IMAGE_API_URL + (posterPath ?: ""),
        backdropPath = IMAGE_API_URL + (backdropPath ?: ""),
        voteAverage = voteAverage ?: 0f,
        overview = overview ?: "",
        genres = (genreIds?.map { genreMap[it] ?: "" } ?: listOf())
            .joinToString(", ")
    )
}