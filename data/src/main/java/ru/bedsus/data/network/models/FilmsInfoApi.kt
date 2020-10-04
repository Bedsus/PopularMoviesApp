package ru.bedsus.data.network.models

import com.google.gson.annotations.SerializedName

class FilmsInfoApi(
    @SerializedName("page") val page: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("results") val results: List<FilmApi>?
)