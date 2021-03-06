package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieReview
import br.com.brunoccbertolini.domain.util.Resource

interface GetMovieReviewUseCase {
    suspend operator fun invoke(id: Int): Resource<MovieReview>
}