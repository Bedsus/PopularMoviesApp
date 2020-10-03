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

class FilmListAdapter : ListAdapter<Film, FilmListAdapter.ViewHolder>(FilmDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_film_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Film) {
            itemView.vFilmName.text = item.title
            Picasso.get()
                .load(item.posterPath)
                .fit()
                .into(itemView.vFilmImage)
        }
    }
}