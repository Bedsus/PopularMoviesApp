package ru.bedsus.data.room.mapper

import ru.bedsus.data.room.model.FilmEntity
import ru.bedsus.domain.model.Film

internal fun FilmEntity.fromEntity(): Film {
    return Film(
        id = id,
        title = title,
        releaseDate = releaseDate,
        popularity = popularity,
        voteCount = voteCount,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        overview = overview,
        genres = genres
    )
}

internal fun Film.toEntity(): FilmEntity {
    return FilmEntity(
        id = id,
        title = title,
        releaseDate = releaseDate,
        popularity = popularity,
        voteCount = voteCount,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        overview = overview,
        genres = genres
    )
}