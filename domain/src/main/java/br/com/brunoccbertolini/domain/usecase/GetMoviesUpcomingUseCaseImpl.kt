package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource


class GetMoviesUpcomingUseCaseImpl(
    private val repository: MoviesRepository
): GetMoviesUpcomingUseCase {
    override suspend fun invoke(): Resource<MoviesListResponse> =
        repository.getMoviesUpComing()
}