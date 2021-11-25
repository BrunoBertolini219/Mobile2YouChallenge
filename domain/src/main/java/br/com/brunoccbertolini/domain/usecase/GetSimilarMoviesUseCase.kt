package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.util.Resource

interface GetSimilarMoviesUseCase {
    suspend operator fun invoke(id: Int): Resource<MoviesList>
}