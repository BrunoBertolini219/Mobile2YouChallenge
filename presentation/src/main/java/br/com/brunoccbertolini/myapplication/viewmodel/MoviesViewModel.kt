package br.com.brunoccbertolini.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunoccbertolini.domain.model.MoviesListResponse
import br.com.brunoccbertolini.domain.usecase.GetMoviesNowPlayingUseCaseImpl
import br.com.brunoccbertolini.domain.usecase.GetMoviesPopularUseCaseImpl
import br.com.brunoccbertolini.domain.usecase.GetMoviesTopRatedUseCaseImpl
import br.com.brunoccbertolini.domain.usecase.GetMoviesUpcomingUseCaseImpl
import br.com.brunoccbertolini.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesNowPlayingUseCase: GetMoviesNowPlayingUseCaseImpl,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCaseImpl,
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCaseImpl,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCaseImpl

) : ViewModel() {

    private val _nowPlayingLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val nowPlayingLiveData: LiveData<Resource<MoviesListResponse>> = _nowPlayingLiveData
    var nowPlayingPage = 1
    var nowPlayingResponse: MoviesListResponse? = null

    private val _upcomingLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val upcomingLiveData: LiveData<Resource<MoviesListResponse>> = _upcomingLiveData
    var upcomingPage = 1
    var upcomingResponse: MoviesListResponse? = null

    private val _topRatedLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val topRatedLiveData: LiveData<Resource<MoviesListResponse>> = _topRatedLiveData
    var topRatedPage = 1
    var topRatedResponse: MoviesListResponse? = null

    private val _popularLiveData = MutableLiveData<Resource<MoviesListResponse>>()
    val popularLiveData: LiveData<Resource<MoviesListResponse>> = _popularLiveData
    var popularPage = 1
    var popularResponse: MoviesListResponse? = null


    fun getMoviesNowPlaying() = viewModelScope.launch {

        _nowPlayingLiveData.postValue(Resource.Loading())
        val response = getMoviesNowPlayingUseCase.invoke()
        _nowPlayingLiveData.postValue(response)

    }

    init {
        getMoviesNowPlaying()
        getMoviesPopular()
        getMoviesTopRated()
        getMoviesUpcoming()
    }

    fun getMoviesUpcoming() = viewModelScope.launch {
        _upcomingLiveData.postValue(Resource.Loading())
        val response = getMoviesUpcomingUseCase.invoke()
        _upcomingLiveData.postValue(response)
    }

    fun getMoviesTopRated() = viewModelScope.launch {
        _topRatedLiveData.postValue(Resource.Loading())
        val response = getMoviesTopRatedUseCase.invoke()
        _topRatedLiveData.postValue(response)

    }

    fun getMoviesPopular() = viewModelScope.launch {
        _popularLiveData.postValue(Resource.Loading())
        val response = getMoviesPopularUseCase.invoke()
        _popularLiveData.postValue(response)

    }

}