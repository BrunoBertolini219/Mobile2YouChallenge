package br.com.brunoccbertolini.domain.repository

import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.model.MovieReviewResponse
import br.com.brunoccbertolini.domain.model.MoviesListResponse

interface MoviesRepository {

    suspend fun getMovies(): MoviesListResponse

    suspend fun getMovieDetails(id: Int): MovieDetailResponse

    suspend fun getMovieReview (id: Int): MovieReviewResponse

}