package ru.bedsus.data.di

import org.koin.dsl.module
import ru.bedsus.data.endpoint.FilmApiService
import ru.bedsus.data.mappers.FilmApiMapperImpl
import ru.bedsus.data.network.NetworkService
import ru.bedsus.data.repository.FilmRepositoryImpl
import ru.bedsus.domain.repository.FilmRepository

val dataModule = module {
    single { NetworkService() }
    single { provideFilmApiService(get()) }
    single { FilmApiMapperImpl() }
    single<FilmRepository> { FilmRepositoryImpl(get(), get()) }
}

private fun provideFilmApiService(networkService: NetworkService): FilmApiService {
    return networkService.create(FilmApiService::class.java)
}