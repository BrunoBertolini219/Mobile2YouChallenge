package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MovieDetail
import br.com.brunoccbertolini.domain.util.Resource

interface GetMovieDetailsUseCase {

    suspend operator fun invoke(id: Int): Resource<MovieDetail>
}