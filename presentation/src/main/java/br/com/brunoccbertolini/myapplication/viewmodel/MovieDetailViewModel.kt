package br.com.brunoccbertolini.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunoccbertolini.domain.model.MovieDetail
import br.com.brunoccbertolini.domain.model.MovieReview
import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.usecase.GetMovieDetailsUseCaseImpl
import br.com.brunoccbertolini.domain.usecase.GetMovieReviewUseCaseImpl
import br.com.brunoccbertolini.domain.usecase.GetSimilarMoviesUseCaseImpl
import br.com.brunoccbertolini.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailsUseCase: GetMovieDetailsUseCaseImpl,
    private val reviewUseCase: GetMovieReviewUseCaseImpl,
    private val similarMoviesUseCase: GetSimilarMoviesUseCaseImpl
) : ViewModel() {

    private val _movieDetailLiveData = MutableLiveData<Resource<MovieDetail>>()
    val movieDetailLiveData: LiveData<Resource<MovieDetail>> = _movieDetailLiveData

    private val _movieReviewLiveData = MutableLiveData<Resource<MovieReview>>()
    val movieReviewLiveData: LiveData<Resource<MovieReview>> = _movieReviewLiveData

    private val _similarMoviesLiveData = MutableLiveData<Resource<MoviesList>>()
    val similarMoviesLiveData: LiveData<Resource<MoviesList>> = _similarMoviesLiveData


    fun getMovieDetail(id: Int) = viewModelScope.launch {
        _movieDetailLiveData.postValue(Resource.Loading())
        val response = detailsUseCase.invoke(id)
        _movieDetailLiveData.postValue(response)
    }

    fun getMovieReview(id: Int) = viewModelScope.launch {
        _movieReviewLiveData.postValue(Resource.Loading())
        val response = reviewUseCase.invoke(id)
        _movieReviewLiveData.postValue(response)
    }

    fun getSimilarMovies(id: Int) = viewModelScope.launch {
        _similarMoviesLiveData.postValue(Resource.Loading())
        val response = similarMoviesUseCase.invoke(id)
        _similarMoviesLiveData.postValue(response)
    }

}