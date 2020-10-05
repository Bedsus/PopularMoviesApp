package ru.bedsus.data.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.dsl.module
import ru.bedsus.data.network.NetworkService
import ru.bedsus.data.network.endpoint.FilmApiService
import ru.bedsus.data.network.repository.FilmApiRepositoryImpl
import ru.bedsus.data.room.DatabaseManager
import ru.bedsus.data.room.dao.FilmDao
import ru.bedsus.data.room.repository.CacheTimeRepositoryImpl
import ru.bedsus.data.room.repository.FilmCacheRepositoryImpl
import ru.bedsus.domain.repository.CacheTimeRepository
import ru.bedsus.domain.repository.FilmApiRepository
import ru.bedsus.domain.repository.FilmCacheRepository

val dataModule = module {

    // network
    single { NetworkService(get()) }
    single { provideFilmApiService(get()) }
    single<FilmApiRepository> { FilmApiRepositoryImpl(get()) }

    // db
    single { DatabaseManager(get()) }
    single { provideFilmDao(get()) }
    single<FilmCacheRepository> { FilmCacheRepositoryImpl(get()) }

    // cache time
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
    single<CacheTimeRepository> { CacheTimeRepositoryImpl(get()) }
}

private fun provideFilmApiService(networkService: NetworkService): FilmApiService {
    return networkService.create(FilmApiService::class.java)
}

private fun provideFilmDao(databaseManager: DatabaseManager): FilmDao {
    return databaseManager.appDatabase.getFilmDao()
}