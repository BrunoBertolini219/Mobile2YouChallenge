package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieReviewResponse

interface GetMovieReviewUseCase {
    suspend operator fun invoke(id: Int):MovieReviewResponse
}