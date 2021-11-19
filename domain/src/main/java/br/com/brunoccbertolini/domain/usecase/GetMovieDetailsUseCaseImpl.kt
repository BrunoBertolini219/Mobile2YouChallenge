package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.util.ResponseHandler

class GetMovieDetailsUseCaseImpl(

): GetMovieDetailsUseCase {
    override suspend fun invoke(id: Int): ResponseHandler<MovieDetailResponse> {
        TODO("Not yet implemented")
    }

}