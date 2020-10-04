package ru.bedsus.popularmoviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_film_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bedsus.domain.model.Film
import ru.bedsus.popularmoviesapp.R
import ru.bedsus.popularmoviesapp.ResultRequest
import ru.bedsus.popularmoviesapp.vm.FilmViewModel
import timber.log.Timber

class FilmInfoFragment : Fragment() {

    private val viewModel by viewModel<FilmViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val filmId = arguments?.getInt(FILM_ID_TAG) ?:
            throw IllegalStateException("Номер фильма не пепредан!")
        viewModel.filmInfoLiveData.observe(viewLifecycleOwner) {
            vProgressBar.hide()
            when (it) {
                is ResultRequest.Success -> showFilmInfo(it.data)
                is ResultRequest.Error -> {
                    Timber.e(it.throwable)
                }
                ResultRequest.Loading -> vProgressBar.show()
            }
        }
        viewModel.loadFilmInfoById(filmId)
    }

    private fun showFilmInfo(film: Film) {
        vFilmName.text = film.title
        Picasso.get()
            .load(film.posterPath)
            .fit()
            .into(vFilmImage)
    }

    companion object {
        const val FILM_ID_TAG = "FILM_ID_TAG"
    }
}