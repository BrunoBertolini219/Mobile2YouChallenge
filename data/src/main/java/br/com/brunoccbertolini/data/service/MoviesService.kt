package br.com.brunoccbertolini.data.service


import br.com.brunoccbertolini.data.model.MovieDetailResponse
import br.com.brunoccbertolini.data.model.MovieReviewResponse
import br.com.brunoccbertolini.data.model.MoviesListResponse
import br.com.brunoccbertolini.data.util.Constraints.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        page: Int? = 1
    ): Response<MoviesListResponse>

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        page: Int? = 1
    ): Response<MoviesListResponse>

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        page:Int? = 1
    ): Response<MoviesListResponse>

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        page:Int? = 1
    ): Response<MoviesListResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id")
        id: Int,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MoviesListResponse>


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")
        id: Int,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id")
        id: Int,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovieReviewResponse>


}