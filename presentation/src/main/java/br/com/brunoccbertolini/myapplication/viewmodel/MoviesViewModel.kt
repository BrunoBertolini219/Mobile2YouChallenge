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



    init {
        getMoviesNowPlaying()
    }

    fun getMoviesNowPlaying() = viewModelScope.launch {

        _nowPlayingLiveData.postValue(Resource.Loading())
        val response = getMoviesNowPlayingUseCase.invoke()
        _nowPlayingLiveData.postValue(response)

        nowPlayingPage++
        if(nowPlayingResponse == null){
            nowPlayingResponse = response.data
    }else{
        val oldMovies = nowPlayingResponse?.results
        val newAMovies = response.data?.results

    }

    fun getMoviesUpcoming() = viewModelScope.launch {

    }

    fun getMoviesTopRated() = viewModelScope.launch {

    }

    fun getMoviesPopular() = viewModelScope.launch {

    }

    fun handlingPagination(){


        }
    }

}