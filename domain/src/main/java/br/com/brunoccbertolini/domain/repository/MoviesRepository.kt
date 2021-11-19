package br.com.brunoccbertolini.domain.repository

import br.com.brunoccbertolini.domain.model.MoviesListResponse

interface MoviesRepository {
    suspend fun getMovies(): MoviesListResponse

}