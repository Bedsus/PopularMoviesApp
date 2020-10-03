package ru.bedsus.domain.model

data class Film(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val popularity: Float,
    val voteCount: Int,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Float,
    val overview: String
)