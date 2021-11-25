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
class GetMoviesPopularUseCaseImplTest{
    private lateinit var getMoviesPopular: GetMoviesPopularUseCaseImpl

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val moviesRepository: MoviesRepository = spyk()


    @Before
    fun setUp(){
        getMoviesPopular = GetMoviesPopularUseCaseImpl(moviesRepository)
    }


    @Test
    fun `GetMoviesNowPlayingUseCase called, returns repository should be called`() = mainCoroutineRule.runBlockingTest {
        getMoviesPopular.invoke()

        verify { runBlocking{moviesRepository.getMoviesPopular() } }

    }


}