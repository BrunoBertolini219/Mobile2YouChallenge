package br.com.brunoccbertolini.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import br.com.brunoccbertolini.data.mapper.toDomainMovieDetailsResponse
import br.com.brunoccbertolini.data.mapper.toDomainMovieReviewsResponse
import br.com.brunoccbertolini.data.mapper.toDomainMoviesResponse
import br.com.brunoccbertolini.data.service.MoviesService
import br.com.brunoccbertolini.data.util.MoviesPagingDataSource
import br.com.brunoccbertolini.domain.model.MovieDetail
import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.domain.model.MovieReview
import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
) : MoviesRepository {

    fun getMoviesListLiveData(): LiveData<PagingData<MovieListItem>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { MoviesPagingDataSource(moviesService) }
        ).liveData

    }

    override suspend fun getMoviesUpComing(): Resource<MoviesList> =
        handleMoviesListResponse(moviesService.getMoviesUpcoming())

    override suspend fun getMoviesPopular(): Resource<MoviesList> =
        handleMoviesListResponse(moviesService.getMoviesPopular())

    override suspend fun getMoviesNowPlaying(): Resource<MoviesList> =
        handleMoviesListResponse(moviesService.getMoviesNowPlaying())

    override suspend fun getMoviesTopRated(): Resource<MoviesList> =
        handleMoviesListResponse(moviesService.getMoviesTopRated())

    override suspend fun getSimilarMovies(id: Int): Resource<MoviesList> =
        handleMoviesListResponse(moviesService.getSimilarMovies(id))

    override suspend fun getMovieDetails(id: Int): Resource<MovieDetail> {
        val response = moviesService.getMovieDetails(id)
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it.toDomainMovieDetailsResponse())
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

    override suspend fun getMovieReview(id: Int): Resource<MovieReview> {
        val response = moviesService.getMovieReviews(id)
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it.toDomainMovieReviewsResponse())
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

private fun handleMoviesListResponse(response: Response<br.com.brunoccbertolini.data.model.MoviesListResponse>): Resource<MoviesList> {
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


