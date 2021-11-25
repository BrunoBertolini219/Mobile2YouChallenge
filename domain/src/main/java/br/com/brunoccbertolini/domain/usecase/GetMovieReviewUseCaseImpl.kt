package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieReview
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import javax.inject.Inject

class GetMovieReviewUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetMovieReviewUseCase {
    override suspend fun invoke(id: Int): Resource<MovieReview> =
        repository.getMovieReview(id)
}