package ru.bedsus.popularmoviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_film_item.view.*
import ru.bedsus.domain.model.Film
import ru.bedsus.popularmoviesapp.R
import ru.bedsus.popularmoviesapp.adapter.FilmListAdapter.FilmClick

class FilmListAdapter : ListAdapter<Film, FilmListAdapter.ViewHolder>(FilmDiffUtils) {

    var filmClick = FilmClick { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_film_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            filmClick.onClick(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Film) {
            itemView.vFilmName.text = item.title
            Picasso.get()
                .load(item.posterPath)
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .fit()
                .into(itemView.vFilmImage)
        }
    }

    fun interface FilmClick {
        fun onClick(film: Film)
    }
}