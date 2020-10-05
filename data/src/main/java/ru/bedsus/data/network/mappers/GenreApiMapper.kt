package ru.bedsus.data.network.mappers

import ru.bedsus.data.network.models.GenreApi
import ru.bedsus.domain.model.Genre

internal fun GenreApi.toModel(): Genre {
    return Genre(
        id = id ?: 0,
        name = name ?: ""
    )
}