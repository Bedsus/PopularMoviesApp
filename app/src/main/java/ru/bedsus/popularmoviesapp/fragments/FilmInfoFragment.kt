package ru.bedsus.popularmoviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_film_info.*
import kotlinx.android.synthetic.main.fragment_film_info.view.*
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
        val view = inflater.inflate(R.layout.fragment_film_info, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.vToolbar)
        view.vToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val filmId = arguments?.getInt(FILM_ID_TAG) ?:
            throw IllegalStateException("Not found film id!")
        viewModel.filmInfoLiveData.observe(viewLifecycleOwner) {
            vProgressBar.hide()
            when (it) {
                is ResultRequest.Success -> showFilmInfo(it.data)
                is ResultRequest.Error -> Timber.e(it.throwable)
                ResultRequest.Loading -> vProgressBar.show()
            }
        }
        viewModel.loadFilmInfoById(filmId)
    }

    private fun showFilmInfo(film: Film) {
        vCollapsingToolbar.title = film.title
        Picasso.get()
            .load(film.backdropPath)
            .into(vBackgroundImage)
        vFilmName.text = film.title
        Picasso.get()
            .load(film.posterPath)
            .placeholder(R.drawable.ic_baseline_local_movies_24)
            .fit()
            .into(vFilmImage)
        vFilmYear.text = film.year
        vRatingBar.rating = film.voteAverage
        vRatingText.text = film.voteAverage.toString()
        vOverviewText.text = film.overview
        vGenresText.text = film.genres
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).setSupportActionBar(null)
    }

    companion object {
        const val FILM_ID_TAG = "FILM_ID_TAG"
    }
}