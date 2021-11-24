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
import br.com.brunoccbertolini.myapplication.util.Event
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

    private val _nowPlayingLiveData = MutableLiveData<Event<Resource<MoviesListResponse>>>()
    val nowPlayingLiveData: LiveData<Event<Resource<MoviesListResponse>>> = _nowPlayingLiveData

    private val _upcomingLiveData = MutableLiveData<Event<Resource<MoviesListResponse>>>()
    val upcomingLiveData: LiveData<Event<Resource<MoviesListResponse>>> = _upcomingLiveData

    private val _topRatedLiveData = MutableLiveData<Event<Resource<MoviesListResponse>>>()
    val topRatedLiveData: LiveData<Event<Resource<MoviesListResponse>>> = _topRatedLiveData

    private val _popularLiveData = MutableLiveData<Event<Resource<MoviesListResponse>>>()
    val popularLiveData: LiveData<Event<Resource<MoviesListResponse>>> = _popularLiveData

    init {
        getMoviesNowPlaying()
        getMoviesUpcoming()
        getMoviesPopular()
        getMoviesTopRated()
    }


    fun getMoviesNowPlaying() = viewModelScope.launch {

        _nowPlayingLiveData.postValue(Event(Resource.Loading()))
        val response = getMoviesNowPlayingUseCase.invoke()
        _nowPlayingLiveData.postValue(Event(response))

    }

    fun getMoviesUpcoming() = viewModelScope.launch {
        _upcomingLiveData.postValue(Event(Resource.Loading()))
        val response = getMoviesUpcomingUseCase.invoke()
        _upcomingLiveData.postValue(Event(response))

    }

    fun getMoviesTopRated() = viewModelScope.launch {
        _topRatedLiveData.postValue(Event(Resource.Loading()))
        val response = getMoviesTopRatedUseCase.invoke()
        _topRatedLiveData.postValue(Event(response))

    }

    fun getMoviesPopular() = viewModelScope.launch {
        _popularLiveData.postValue(Event(Resource.Loading()))
        val response = getMoviesPopularUseCase.invoke()
        _popularLiveData.postValue(Event(response))

    }
}