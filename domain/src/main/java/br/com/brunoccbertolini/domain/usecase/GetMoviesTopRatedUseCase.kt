package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.util.Resource

interface GetMoviesTopRatedUseCase {
    suspend operator fun invoke(): Resource<MoviesList>
}