package br.com.brunoccbertolini.data

import br.com.brunoccbertolini.data.model.MovieDetailResponse
import br.com.brunoccbertolini.data.model.MovieReviewResponse
import br.com.brunoccbertolini.data.model.MoviesListResponse
import br.com.brunoccbertolini.domain.model.*


object FakeModels {

    // :domain/model

    val listGenres = listOf<Genres>(
        Genres("null.name"),
        Genres("null2.name")
    )


    val listMovieItems = listOf<MovieListItem>(
        MovieListItem(
            poster_path = "null.png",
            id = 0
        ),
        MovieListItem(
            poster_path = "null2.png",
            id = 1
        )

    )
    val listMovieReviewItem = listOf<MovieReviewItem>(
        MovieReviewItem(
            author = "null.author",
            content = "null.content",
        ),
        MovieReviewItem(
            author = "null2.author",
            content = "null2.content",
        )
    )

    val movieDetail = MovieDetail(
        poster_path = "null.png",
        overview = "null",
        listGenres,
        title = "null.title",
        backdrop_path = "null.path",
        vote_average = 0f,
        runtime = 0
    )

    val moviesList = MoviesList(
        page = 1,
        results = listMovieItems

    )

    val movieReviews = MovieReview(
        results = listMovieReviewItem
    )


    // :data/model

    val listMovieItemsResponse = listOf(
        br.com.brunoccbertolini.data.model.MovieListItem(
            posterPath = "null.png",
            id = 0
        ),
        br.com.brunoccbertolini.data.model.MovieListItem(
            posterPath = "null2.png",
            id = 1
        )
    )

    val moviesListResponse = MoviesListResponse(
        page = 1,
        results = listMovieItemsResponse
    )

    val reviewItemResponse = listOf(
        br.com.brunoccbertolini.data.model.MovieReviewItem(
            author = "null.author",
            content = "null.content"
        ),
        br.com.brunoccbertolini.data.model.MovieReviewItem(
            author = "null2.author",
            content = "null2.content"
        )
    )

    val movieReviewResponse = MovieReviewResponse(
        results = reviewItemResponse
    )

    val listGenresResponse = listOf(
        br.com.brunoccbertolini.data.model.Genres(
            name = "null.name",
        ),
        br.com.brunoccbertolini.data.model.Genres(
            name = "null2.name",
        )
    )

    val movieDetailResponse = MovieDetailResponse(
        poster_path = "null.png",
        overview = "null",
        listGenresResponse,
        title = "null.title",
        backdropPath = "null.path",
        voteAverage = 0f,
        runtime = 0
    )

}
