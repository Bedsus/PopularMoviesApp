package ru.bedsus.data.network.endpoint

import io.reactivex.Single
import retrofit2.http.GET
import ru.bedsus.data.network.models.FilmsInfoApi
import ru.bedsus.data.network.models.GenreInfoApi

interface FilmApiService {

    @GET("movie/popular")
    fun getPopularFilms(): Single<FilmsInfoApi>

    @GET("genre/movie/list")
    fun getGenreList(): Single<GenreInfoApi>
}