package ru.bedsus.domain.model

import java.text.SimpleDateFormat
import java.util.*

data class Film(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val popularity: Float,
    val voteCount: Int,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Float,
    val overview: String,
    val genres: String
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val year: String
        get() {
            val date = dateFormat.parse(releaseDate)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return "(${calendar.get(Calendar.YEAR)})"
        }
}