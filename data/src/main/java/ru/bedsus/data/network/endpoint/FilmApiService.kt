package ru.bedsus.data.network.endpoint

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bedsus.data.network.models.FilmsInfoApi

interface FilmApiService {

    @GET("movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String
    ): Single<FilmsInfoApi>
}