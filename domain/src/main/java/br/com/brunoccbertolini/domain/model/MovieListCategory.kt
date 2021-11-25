package br.com.brunoccbertolini.domain.model

data class MovieListCategory(
    val category: String,
    val movieList: List<MovieListItem>
)