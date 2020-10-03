package ru.bedsus.domain.repository

interface FilmModelMapper<E, M> {
    fun toModel(from: E): M
}