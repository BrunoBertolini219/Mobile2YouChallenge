package br.com.brunoccbertolini.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.brunoccbertolini.data.mapper.toDomainMoviesResponse
import br.com.brunoccbertolini.data.service.MoviesService
import br.com.brunoccbertolini.domain.model.MovieListItem

class MoviesPagingDataSource(
    private val service: MoviesService
) : PagingSource<Int, MovieListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItem> {

        try {
            val pageNumber: Int = params.key ?: 1
            val response = service.getMoviesNowPlaying(page = pageNumber)
            val responseBodyToDomain = response.body()?.toDomainMoviesResponse()
            val data: List<MovieListItem> = responseBodyToDomain?.results ?: listOf()




             return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = pageNumber.plus(1)
            )


        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieListItem>): Int {
        return 0
    }


}