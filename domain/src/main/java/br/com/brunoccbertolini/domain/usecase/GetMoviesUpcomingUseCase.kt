package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.util.Resource

interface GetMoviesUpcomingUseCase {
    suspend operator fun invoke(): Resource<MoviesListResponse>
}