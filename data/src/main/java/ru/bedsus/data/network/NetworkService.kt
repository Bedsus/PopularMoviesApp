package ru.bedsus.data.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bedsus.data.R
import java.util.concurrent.TimeUnit

class NetworkService(applicationContext: Context) {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val apiKeyInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val apiKey = applicationContext.getString(R.string.apiKey)
            val url = request.url
                .newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            request = request.newBuilder()
                .url(url)
                .build()
            return chain.proceed(request)
        }
    }

    private val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)

    private val retrofit: Retrofit = retrofitBuilder.client(httpClientBuilder.build())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    private companion object {
        private const val BASE_URL = "http://api.themoviedb.org/3/"
        private const val READ_TIMEOUT_SEC = 30L
        private const val CONNECT_TIMEOUT_SEC = 10L
    }
}