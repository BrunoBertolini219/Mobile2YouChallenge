package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.util.Resource
import br.com.brunoccbertolini.domain.util.ResponseHandler

interface GetMoviesNowPlayingUseCase {
    suspend operator fun invoke (): Resource<MoviesListResponse>

}