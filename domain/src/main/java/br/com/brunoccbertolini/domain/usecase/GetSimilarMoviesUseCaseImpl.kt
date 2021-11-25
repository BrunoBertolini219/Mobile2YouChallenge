package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import javax.inject.Inject

class GetSimilarMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
) : GetSimilarMoviesUseCase {
    override suspend fun invoke(id: Int): Resource<MoviesList> =
        repository.getSimilarMovies(id)
}