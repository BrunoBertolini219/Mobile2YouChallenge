package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.util.ResponseHandler

interface GetMoviesUseCase {
    suspend operator fun invoke (): MoviesListResponse

}