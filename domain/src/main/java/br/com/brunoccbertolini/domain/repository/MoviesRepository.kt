package br.com.brunoccbertolini.domain.repository

import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.model.MovieReviewResponse
import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.util.Resource

interface MoviesRepository {

    suspend fun getMoviesUpComing(): Resource<MoviesListResponse>

    suspend fun getMoviesPopular(): Resource<MoviesListResponse>

    suspend fun getMoviesTopRated(): Resource<MoviesListResponse>

    suspend fun getMoviesNowPlaying(): Resource<MoviesListResponse>

    suspend fun getSimilarMovies(id:Int): Resource<MoviesListResponse>

    suspend fun getMovieDetails(id: Int): MovieDetailResponse

    suspend fun getMovieReview (id: Int): MovieReviewResponse

}