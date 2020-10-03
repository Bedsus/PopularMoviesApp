package ru.bedsus.popularmoviesapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.bedsus.domain.model.Film
import ru.bedsus.domain.usecase.FilmsUserCase
import ru.bedsus.popularmoviesapp.ResultRequest

class FilmViewModel(
    private val userCase: FilmsUserCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _filmsLiveData = MutableLiveData<ResultRequest<List<Film>>>()
    val filmsLiveData: LiveData<ResultRequest<List<Film>>>
        get() = _filmsLiveData

    fun loadPopularFilms() {
        _filmsLiveData.value = ResultRequest.Loading
        compositeDisposable.add(
            userCase.getPopularFilmList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _filmsLiveData.value = ResultRequest.Success(it)
                }, {
                    _filmsLiveData.value = ResultRequest.Error(it)
                })
        )
    }
}