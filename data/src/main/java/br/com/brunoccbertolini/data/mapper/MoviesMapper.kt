package br.com.brunoccbertolini.data.mapper


import br.com.brunoccbertolini.domain.model.*


fun br.com.brunoccbertolini.data.model.MoviesListResponse.toDomainMoviesResponse() =
    MoviesListResponse(
        page = page,
        results = results?.map { it.toDomainMovieListItem()}
    )




fun br.com.brunoccbertolini.data.model.MovieDetailResponse.toDomainMovieDetailsResponse() =
    MovieDetailResponse(
        poster_path = poster_path,
        overview = overview,
        genres = genres.map { it.toDomainDetailGenres() },
        title = title,
        backdrop_path = backdropPath,
        vote_average = voteAverage,
        runtime = runtime

    )

fun br.com.brunoccbertolini.data.model.MovieReviewResponse.toDomainMovieReviewsResponse() =
    MovieReviewResponse(
        results = results.map { it.toDomainReviewItem() }
    )


fun br.com.brunoccbertolini.data.model.Genres.toDomainDetailGenres() =
    Genres( name = name )

fun br.com.brunoccbertolini.data.model.MovieReviewItem.toDomainReviewItem() =
    MovieReviewItem(
        author = author,
        content = content
    )

fun br.com.brunoccbertolini.data.model.MovieListItem.toDomainMovieListItem() =
    MovieListItem(
        poster_path = posterPath,
        id = id
    )


