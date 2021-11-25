package br.com.brunoccbertolini.domain.usecase

import br.com.brunoccbertolini.domain.repository.MoviesRepository
import br.com.brunoccbertolini.domain.util.MainCoroutineRule
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class GetMovieDetailsUseCaseImplTest{
    private lateinit var getMovieDetails: GetMovieDetailsUseCaseImpl

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val moviesRepository: MoviesRepository = spyk()


    @Before
    fun setUp(){
        getMovieDetails = GetMovieDetailsUseCaseImpl(moviesRepository)
    }


    @Test
    fun `GetMoviesNowPlayingUseCase called, returns repository should be called`() = mainCoroutineRule.runBlockingTest {
        getMovieDetails.invoke(1)

        verify {
            runBlocking { moviesRepository.getMovieDetails(1) }}

        }
    }