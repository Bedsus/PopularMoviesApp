package ru.bedsus.data.room.repository

import android.content.SharedPreferences
import ru.bedsus.domain.repository.CacheTimeRepository

class CacheTimeRepositoryImpl(
    private val sharedPref: SharedPreferences
): CacheTimeRepository {

    override val lastSaveTimeToCache: Long
        get() = sharedPref.getLong(CACHE_TIME_TAG, 0L)

    override fun saveSaveTime(time: Long) {
        sharedPref.edit()
            .putLong(CACHE_TIME_TAG, time)
            .apply()
    }

    companion object {
        private const val CACHE_TIME_TAG = "LAST_SAVE_TIME_TO_CACHE_TAG"
    }
}