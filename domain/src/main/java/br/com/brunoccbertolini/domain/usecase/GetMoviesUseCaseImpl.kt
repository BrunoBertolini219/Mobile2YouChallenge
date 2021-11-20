package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository

class GetMoviesUseCaseImpl(
    private val repository: MoviesRepository
) : GetMoviesUseCase {
    override suspend fun invoke(): MoviesListResponse =
        repository.getMovies()
}