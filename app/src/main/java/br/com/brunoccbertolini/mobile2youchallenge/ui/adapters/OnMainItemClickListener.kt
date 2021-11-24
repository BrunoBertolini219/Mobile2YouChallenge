package br.com.brunoccbertolini.mobile2youchallenge.ui.adapters

import br.com.brunoccbertolini.domain.model.MovieListItem

interface OnMainItemClickListener {
    fun onMainItemClick(movie: MovieListItem, position: Int)
}