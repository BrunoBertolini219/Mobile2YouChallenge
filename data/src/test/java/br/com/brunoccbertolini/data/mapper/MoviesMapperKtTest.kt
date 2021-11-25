package br.com.brunoccbertolini.data.mapper

import br.com.brunoccbertolini.data.FakeModels.movieDetail
import br.com.brunoccbertolini.data.FakeModels.movieDetailResponse
import br.com.brunoccbertolini.data.FakeModels.movieReviewResponse
import br.com.brunoccbertolini.data.FakeModels.movieReviews
import br.com.brunoccbertolini.data.FakeModels.moviesList
import br.com.brunoccbertolini.data.FakeModels.moviesListResponse
import org.junit.Assert
import org.junit.Test

class MoviesMapperKtTest {

    @Test
    fun `convert MoviesListResponse from data module to MoviesList from domain module, return correct`(){
        val fakeMoviesListResponse = moviesListResponse

        val domainMoviesList = fakeMoviesListResponse.toDomainMoviesResponse()

        Assert.assertEquals(domainMoviesList, moviesList)
    }

    @Test
    fun `convert MovieDetailResponse from data module to MovieDetail from domain module, return correct`(){
        val fakeMovieDetailResponse = movieDetailResponse

        val domainMovieDetail = fakeMovieDetailResponse.toDomainMovieDetailsResponse()

        Assert.assertEquals(domainMovieDetail, movieDetail)
    }

    @Test
    fun `convert MovieReviewPesponse from data module to MovieReview from domain module, return correct`(){
        val fakeMovieReviewResponse = movieReviewResponse

        val domainMovieReview = fakeMovieReviewResponse.toDomainMovieReviewsResponse()

        Assert.assertEquals(domainMovieReview, movieReviews)
    }




}