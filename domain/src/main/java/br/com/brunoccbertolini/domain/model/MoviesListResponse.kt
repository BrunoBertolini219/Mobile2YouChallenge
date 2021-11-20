package br.com.brunoccbertolini.domain.model

data class MoviesListResponse(
    val page: Int?,
    val results: List<MovieListItem>?
)

fun getResults(results: List<MovieListItem>?){

}

data class MovieListItem(

    val poster_path: String?,
    val id: Int?
)