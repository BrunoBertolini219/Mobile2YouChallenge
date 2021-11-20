package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import javax.inject.Inject

class GetMoviesTopRatedUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
):GetMoviesTopRatedUseCase {
    override suspend fun invoke(): Resource<MoviesListResponse> =
        repository.getMoviesTopRated()

}