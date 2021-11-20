package br.com.brunoccbertolini.data.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    val page: Int?,
    val results: List<MovieListItem>?
)

data class MovieListItem(
    @SerializedName("poster_path")
    val posterPath: String?,
    val id: Int?
)