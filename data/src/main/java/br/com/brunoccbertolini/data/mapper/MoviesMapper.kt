package br.com.brunoccbertolini.data.mapper


import br.com.brunoccbertolini.data.model.MovieDetailResponse
import br.com.brunoccbertolini.data.model.MovieReviewResponse
import br.com.brunoccbertolini.data.model.MoviesListResponse
import br.com.brunoccbertolini.domain.model.*


        fun MoviesListResponse.toDomainMoviesResponse() =
            MoviesList(
                page = page,
                results = results?.map { it.toDomainMovieListItem() }
            )


        fun MovieDetailResponse.toDomainMovieDetailsResponse() =
            MovieDetail(
                poster_path = poster_path,
                overview = overview,
                genres = genres.map { it.toDomainDetailGenres() },
                title = title,
                backdrop_path = backdropPath,
                vote_average = voteAverage,
                runtime = runtime
            )

        fun MovieReviewResponse.toDomainMovieReviewsResponse() =
            MovieReview(
                results = results.map { it.toDomainReviewItem() }
            )


        fun br.com.brunoccbertolini.data.model.Genres.toDomainDetailGenres() =
            Genres(name = name)

        fun br.com.brunoccbertolini.data.model.MovieReviewItem.toDomainReviewItem() =
            MovieReviewItem(
                author = author,
                content = content,
            )

        fun br.com.brunoccbertolini.data.model.MovieListItem.toDomainMovieListItem() =
            MovieListItem(
                poster_path = posterPath,
                id = id
            )
