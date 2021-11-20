package br.com.brunoccbertolini.data.mapper


import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.domain.model.MoviesListResponse


fun MoviesListResponse.toDataMoviesResponse() =
    br.com.brunoccbertolini.data.model.MoviesListResponse(
        page = page,
        results = results?.map { it.toDataMovieListItem() }
    )


fun MovieListItem.toDataMovieListItem() =
    br.com.brunoccbertolini.data.model.MovieListItem(
        posterPath = poster_path,
        id = id
    )

fun br.com.brunoccbertolini.data.model.MoviesListResponse.toDomainMoviesResponse() =
    MoviesListResponse(
        page = page,
        results = results?.map { it.toDomainMovieListItem()}
    )


fun br.com.brunoccbertolini.data.model.MovieListItem.toDomainMovieListItem() =
    MovieListItem(
        poster_path = posterPath,
        id = id
    )

