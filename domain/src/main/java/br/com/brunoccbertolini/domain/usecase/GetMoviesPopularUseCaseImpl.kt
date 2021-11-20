package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource

class GetMoviesPopularUseCaseImpl(
    private val repository: MoviesRepository
) : GetMoviesPopularUseCase {
    override suspend fun invoke(): Resource<MoviesListResponse> = repository.getMoviesPopular()
}