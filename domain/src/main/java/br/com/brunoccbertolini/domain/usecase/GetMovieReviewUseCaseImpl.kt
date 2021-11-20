package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieReviewResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieReviewUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetMovieReviewUseCase {
    override suspend fun invoke(id: Int): MovieReviewResponse =
        repository.getMovieReview(id)
}