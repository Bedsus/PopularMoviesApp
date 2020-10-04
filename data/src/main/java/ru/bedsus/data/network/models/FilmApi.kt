package ru.bedsus.data.network.models

import com.google.gson.annotations.SerializedName

class FilmApi(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("popularity")  val popularity: Float?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?
)