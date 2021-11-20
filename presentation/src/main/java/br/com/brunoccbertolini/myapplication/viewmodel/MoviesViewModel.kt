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


        fun getMoviesNowPlaying() = viewModelScope.launch {
                _nowPlayingLiveData.postValue(Resource.Loading())
                val response = getMoviesNowPlayingUseCase.invoke()
                _nowPlayingLiveData.postValue(response)
        }
}