package br.com.brunoccbertolini.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val poster_path: String?,
    val overview: String?,
    val genres: List<Genres>,
    val title: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    val runtime: Int?
)
data class Genres(
    val name: String?
)