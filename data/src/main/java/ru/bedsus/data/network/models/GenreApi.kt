package ru.bedsus.data.network.models

import com.google.gson.annotations.SerializedName

class GenreApi(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
)