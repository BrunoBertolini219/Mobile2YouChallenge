package br.com.brunoccbertolini.mobile2youchallenge.util

import androidx.recyclerview.widget.DiffUtil
import br.com.brunoccbertolini.domain.model.MovieListCategory

class SectionDiffUtil: DiffUtil.ItemCallback<MovieListCategory>() {
    override fun areItemsTheSame(oldItem: MovieListCategory, newItem: MovieListCategory): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: MovieListCategory, newItem: MovieListCategory): Boolean {
        return false
    }
}