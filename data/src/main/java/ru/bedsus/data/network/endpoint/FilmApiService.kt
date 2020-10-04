package ru.bedsus.data.network.endpoint

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bedsus.data.network.models.FilmsInfoApi
import ru.bedsus.data.network.models.GenreInfoApi

interface FilmApiService {

    @GET("movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String
    ): Single<FilmsInfoApi>

    @GET("genre/movie/list")
    fun getGenreList(
        @Query("api_key") apiKey: String
    ): Single<GenreInfoApi>
}