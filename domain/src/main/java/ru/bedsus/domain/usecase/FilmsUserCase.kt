package ru.bedsus.domain.usecase

import ru.bedsus.domain.repository.FilmRepository

class FilmsUserCase(private val repository: FilmRepository) {

    fun getPopularFilmList() = repository.getPopularFilmList()
}