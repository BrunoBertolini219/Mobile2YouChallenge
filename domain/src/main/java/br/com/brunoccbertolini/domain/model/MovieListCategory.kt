package br.com.brunoccbertolini.domain.model

data class MovieListCategory(
    val category: String,
    val movielist: List<MovieListItem>
)