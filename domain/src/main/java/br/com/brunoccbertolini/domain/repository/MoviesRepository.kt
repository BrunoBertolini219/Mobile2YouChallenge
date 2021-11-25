package br.com.brunoccbertolini.domain.repository

import br.com.brunoccbertolini.domain.model.MovieDetail
import br.com.brunoccbertolini.domain.model.MovieReview
import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.util.Resource

interface MoviesRepository {

    suspend fun getMoviesUpComing(): Resource<MoviesList>

    suspend fun getMoviesPopular(): Resource<MoviesList>

    suspend fun getMoviesTopRated(): Resource<MoviesList>

    suspend fun getMoviesNowPlaying(): Resource<MoviesList>

    suspend fun getSimilarMovies(id:Int): Resource<MoviesList>

    suspend fun getMovieDetails(id: Int): Resource<MovieDetail>

    suspend fun getMovieReview (id: Int): Resource<MovieReview>

}