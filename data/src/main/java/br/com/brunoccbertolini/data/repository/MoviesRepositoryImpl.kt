package br.com.brunoccbertolini.data.repository

import br.com.brunoccbertolini.data.mapper.toDomainMoviesResponse
import br.com.brunoccbertolini.data.service.MoviesService
import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.model.MovieReviewResponse
import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
) : MoviesRepository {

    override suspend fun getMoviesUpComing(): Resource<MoviesListResponse> =
        handleCocktailListResponse(moviesService.getMoviesUpcoming())

    override suspend fun getMoviesPopular(): Resource<MoviesListResponse> =
        handleCocktailListResponse(moviesService.getMoviesPopular())

    override suspend fun getMoviesNowPlaying(): Resource<MoviesListResponse> =
        handleCocktailListResponse(moviesService.getMoviesNowPlaying())

    override suspend fun getMoviesTopRated(): Resource<MoviesListResponse> =
        handleCocktailListResponse(moviesService.getMoviesTopRated())

    override suspend fun getSimilarMovies(id: Int): Resource<MoviesListResponse> =
        handleCocktailListResponse(moviesService.getSimilarMovies(id))

    override suspend fun getMovieDetails(id: Int): MovieDetailResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieReview(id: Int): MovieReviewResponse {
        TODO("Not yet implemented")
    }


    private fun handleCocktailListResponse(response: Response<br.com.brunoccbertolini.data.model.MoviesListResponse>): Resource<MoviesListResponse> {
        return try {

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it.toDomainMoviesResponse())
                } ?: Resource.Error("An unknown error has ocrrured")
            } else {
                return Resource.Error("An unknown error has ocurred")
            }
        } catch (e: Exception) {
            return Resource.Error(
                "We couldn't reach the server. Check your internet connection",
                null
            )
        }

    }

}