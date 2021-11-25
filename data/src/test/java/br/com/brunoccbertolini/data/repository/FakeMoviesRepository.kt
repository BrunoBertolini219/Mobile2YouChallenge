package br.com.brunoccbertolini.data.repository

import androidx.lifecycle.MutableLiveData
import br.com.brunoccbertolini.data.FakeModels.movieDetail
import br.com.brunoccbertolini.data.FakeModels.movieReviews
import br.com.brunoccbertolini.domain.model.MovieDetail
import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.domain.model.MovieReview
import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.Resource

class FakeMoviesRepository(): MoviesRepository {

    private val moviesList = mutableListOf<MovieListItem>()

    private val observableMovieListItem = MutableLiveData<List<MovieListItem>>(moviesList)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableMovieListItem.postValue(moviesList)
    }

    override suspend fun getMoviesUpComing(): Resource<MoviesList> {
        return checkResourceMoviesListWorks()
    }

    override suspend fun getMoviesPopular(): Resource<MoviesList> {
        return checkResourceMoviesListWorks()
    }

    override suspend fun getMoviesTopRated(): Resource<MoviesList> {
        return checkResourceMoviesListWorks()
    }

    override suspend fun getMoviesNowPlaying(): Resource<MoviesList> {
        return checkResourceMoviesListWorks()
    }

    override suspend fun getSimilarMovies(id: Int): Resource<MoviesList> {
        return checkResourceMoviesListWorks()
    }

    override suspend fun getMovieDetails(id: Int): Resource<MovieDetail> {
        return checkResourceMovieDetailWorks()
    }

    override suspend fun getMovieReview(id: Int): Resource<MovieReview> {
        return checkResourceMovieReviewWorks()
    }

    private fun checkResourceMoviesListWorks(): Resource<MoviesList> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error", null)
        } else {
            Resource.Success(MoviesList(1,listOf()))
        }
    }

    private fun checkResourceMovieDetailWorks(): Resource<MovieDetail> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error", null)
        } else {
            Resource.Success(movieDetail)
        }
    }
    private fun checkResourceMovieReviewWorks(): Resource<MovieReview> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error", null)
        } else {
            Resource.Success(movieReviews)
        }
    }

}