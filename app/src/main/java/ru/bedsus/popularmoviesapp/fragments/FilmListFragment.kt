package ru.bedsus.popularmoviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_film_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bedsus.popularmoviesapp.R
import ru.bedsus.popularmoviesapp.ResultRequest
import ru.bedsus.popularmoviesapp.adapter.FilmListAdapter
import ru.bedsus.popularmoviesapp.fragments.FilmInfoFragment.Companion.FILM_ID_TAG
import ru.bedsus.popularmoviesapp.vm.FilmViewModel
import timber.log.Timber

class FilmListFragment : Fragment() {

    private val viewModel by viewModel<FilmViewModel>()

    private lateinit var adapter: FilmListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film_list, container, false)
        view.vSwipeRefresher.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        view.vSwipeRefresher.setOnRefreshListener {
            viewModel.loadPopularFilms()
        }
        adapter = FilmListAdapter()
        adapter.filmClick = FilmListAdapter.FilmClick { showFilmInfo(it.id) }
        view.vFilmList.layoutManager = LinearLayoutManager(context)
        view.vFilmList.adapter = adapter
        viewModel.filmsLiveData.observe(viewLifecycleOwner) {
            view.vSwipeRefresher.isRefreshing = false
            when (it) {
                is ResultRequest.Success -> adapter.submitList(it.data)
                is ResultRequest.Error -> {
                   Timber.e(it.throwable)
                }
                ResultRequest.Loading -> view.vSwipeRefresher.isRefreshing = true
           }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadPopularFilms()
    }

    private fun showFilmInfo(filmId: Int) {
        val bundle = Bundle()
        bundle.putInt(FILM_ID_TAG, filmId)
        val fragment = FilmInfoFragment()
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.vContainerFragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}