package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource

class GetMoviesNowPlayingUseCaseImpl(
    private val repository: MoviesRepository
) : GetMoviesNowPlayingUseCase {
    override suspend fun invoke(): Resource<MoviesListResponse> =
        repository.getMoviesNowPlaying()
}