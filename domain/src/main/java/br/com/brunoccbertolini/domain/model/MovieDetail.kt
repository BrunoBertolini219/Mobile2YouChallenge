package br.com.brunoccbertolini.domain.model

data class MovieDetail(
    val poster_path: String?,
    val overview: String?,
    val genres: List<Genres>,
    val title: String?,
    val backdrop_path: String?,
    val vote_average: Float?,
    val runtime: Int?
)
data class Genres(
    val name: String?
)