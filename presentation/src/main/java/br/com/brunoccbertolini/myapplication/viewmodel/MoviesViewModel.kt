package br.com.brunoccbertolini.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.usecase.*
import br.com.brunoccbertolini.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesNowPlayingUseCase: GetMoviesNowPlayingUseCaseImpl,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCaseImpl,
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCaseImpl,
    private val getMoviesPopularUseCaseImpl: GetMoviesPopularUseCaseImpl

) : ViewModel() {

    private val _nowPlayingLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val nowPlayingLiveData: LiveData<Resource<MoviesListResponse>> = _nowPlayingLiveData

    private val _upcomingLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val upcomingLiveData: LiveData<Resource<MoviesListResponse>> = _upcomingLiveData

    private val _topRatedLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val topRatedLiveData: LiveData<Resource<MoviesListResponse>> = _topRatedLiveData

    private val _popularLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val popularLiveData: LiveData<Resource<MoviesListResponse>> = _popularLiveData


    fun getMoviesNowPlaying() = viewModelScope.launch {
        _nowPlayingLiveData.postValue(Resource.Loading())
        val response = getMoviesNowPlayingUseCase.invoke()
        _nowPlayingLiveData.postValue(response)
    }

    fun getMoviesUpcoming() = viewModelScope.launch {

    }

    fun getMoviesTopRated() = viewModelScope.launch {

    }

    fun getMoviesPopular() = viewModelScope.launch {

    }
}