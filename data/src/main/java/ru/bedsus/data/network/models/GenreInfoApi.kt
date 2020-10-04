package ru.bedsus.data.network.models

import com.google.gson.annotations.SerializedName

class GenreInfoApi(
    @SerializedName("genres") val genres: List<GenreApi>?
)