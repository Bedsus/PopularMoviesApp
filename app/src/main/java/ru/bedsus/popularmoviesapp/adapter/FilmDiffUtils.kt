package ru.bedsus.popularmoviesapp.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.bedsus.domain.model.Film

object FilmDiffUtils : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}