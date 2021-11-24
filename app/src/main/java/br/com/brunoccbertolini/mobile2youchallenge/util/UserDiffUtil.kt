package br.com.brunoccbertolini.mobile2youchallenge.util

import androidx.recyclerview.widget.DiffUtil
import br.com.brunoccbertolini.domain.model.MovieListItem

class UserDiffUtil: DiffUtil.ItemCallback<MovieListItem>() {
    override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
        return false
    }
}