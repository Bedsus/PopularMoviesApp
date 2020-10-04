package ru.bedsus.domain.usecase

import io.reactivex.Single
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.repository.CacheTimeRepository
import ru.bedsus.domain.repository.FilmApiRepository
import ru.bedsus.domain.repository.FilmCacheRepository
import java.util.concurrent.TimeUnit

class FilmsUserCase(
    private val apiRepository: FilmApiRepository,
    private val cacheRepository: FilmCacheRepository,
    private val cacheTimeManager: CacheTimeRepository
) {

    fun getFilmInfoById(id: Int): Single<Film> {
        return cacheRepository.getFilmInfoById(id)
            .toSingle()
    }

    /**
     * Получение списка популярных фильмов:
     * 1. Получаем список из кеша. При его отсутствии генерируется ошибка
     * 2. Проверяем актуальность кеша. В случае если он устарел генерируется ошибка
     * 3. При любой ошибке чтения кеша загружаем данные из сети
     * 4. При загрузке из сети сохраняем данные в кеш
     * 5. Сортируем список фильмов по популярности
     */
    fun getPopularFilmList(): Single<List<Film>> {
        return cacheRepository.getPopularFilmList()
            .toSingle()
            .doOnSuccess {
                if (isCacheDateExpired()) {
                    throw IllegalStateException(CACHE_DATA_EXPIRED)
                }
            }
            .onErrorResumeNext {
                apiRepository.getPopularFilmList()
                    .doOnSuccess {
                        cacheRepository.saveFilmListToCache(it)
                        cacheTimeManager.saveSaveTime(System.currentTimeMillis())
                    }
            }
            .map { films -> films.sortedByDescending { it.popularity } }
    }

    private fun isCacheDateExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastSaveTime = cacheTimeManager.lastSaveTimeToCache
        return currentTime - lastSaveTime > TimeUnit.HOURS.toMillis(2)
    }

    companion object {
        const val CACHE_DATA_EXPIRED = "Кеш устарел!"
    }
}