package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import javax.inject.Inject

class GetMoviesNowPlayingUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
) : GetMoviesNowPlayingUseCase {
    override suspend fun invoke(): Resource<MoviesList> =
        repository.getMoviesNowPlaying()
}