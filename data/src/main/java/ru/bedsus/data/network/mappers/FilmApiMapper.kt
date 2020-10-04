package ru.bedsus.data.network.mappers

import ru.bedsus.data.network.models.FilmApi
import ru.bedsus.data.resource.DataResource
import ru.bedsus.domain.model.Film

fun FilmApi.toModel(resource: DataResource, genreMap: Map<Int, String>): Film {
    return Film(
        id = id ?: 0,
        title = title ?: "",
        releaseDate = releaseDate ?: "",
        popularity = popularity ?: 0f,
        voteCount = voteCount ?: 0,
        posterPath = resource.imageApiUrl + (posterPath ?: ""),
        backdropPath = resource.imageApiUrl + (backdropPath ?: ""),
        voteAverage = voteAverage ?: 0f,
        overview = overview ?: "",
        genres = (genreIds?.map { genreMap[it] ?: "" } ?: listOf())
            .joinToString(", ")
    )
}