package ru.bedsus.popularmoviesapp


sealed class ResultRequest<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultRequest<T>()
    data class Error(val throwable: Throwable) : ResultRequest<Nothing>()
    object Loading : ResultRequest<Nothing>()
}