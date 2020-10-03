package ru.bedsus.data.endpoint

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bedsus.data.models.FilmsInfoApi

interface FilmApiService {

    @GET("movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String
    ): Single<FilmsInfoApi>
}