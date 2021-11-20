package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieReviewResponse
import br.com.brunoccbertolini.domain.util.ResponseHandler

interface GetMovieReviewUseCase {
    suspend operator fun invoke(id: Int): MovieReviewResponse
}