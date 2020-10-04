package ru.bedsus.domain.repository

interface CacheTimeRepository {

    val lastSaveTimeToCache: Long

    fun saveSaveTime(time: Long)
}