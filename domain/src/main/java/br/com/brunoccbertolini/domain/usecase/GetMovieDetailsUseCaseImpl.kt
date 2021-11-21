package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import javax.inject.Inject


class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetMovieDetailsUseCase {
    override suspend fun invoke(id: Int): Resource<MovieDetailResponse> =
        repository.getMovieDetails(id)

}