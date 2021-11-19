package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.util.ResponseHandler

interface GetMovieDetailsUseCase {

    suspend operator fun invoke(id: Int): ResponseHandler<MovieDetailResponse>
}