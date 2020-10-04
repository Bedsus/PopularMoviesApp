package ru.bedsus.popularmoviesapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bedsus.domain.usecase.FilmsUserCase
import ru.bedsus.popularmoviesapp.vm.FilmViewModel

val appModule = module {
    single { FilmsUserCase(get(), get(), get()) }
    viewModel { FilmViewModel(get()) }
}