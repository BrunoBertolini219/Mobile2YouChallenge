package br.com.brunoccbertolini.myapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.brunoccbertolini.domain.usecase.GetMoviesPopularUseCaseImpl
import br.com.brunoccbertolini.myapplication.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    lateinit var getMoviesPopularUseCaseImpl: GetMoviesPopularUseCaseImpl


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){

    }

    @org.junit.Test
    fun getNowPlayingLiveData() {
    }

    @org.junit.Test
    fun getUpcomingLiveData() {
    }

    @org.junit.Test
    fun getTopRatedLiveData() {
    }

    @org.junit.Test
    fun getPopularLiveData() {
    }

    @org.junit.Test
    fun getMoviesNowPlaying() {
    }

    @org.junit.Test
    fun getMoviesUpcoming() {
    }

    @org.junit.Test
    fun getMoviesTopRated() {
    }

    @org.junit.Test
    fun getMoviesPopular() {
    }
}