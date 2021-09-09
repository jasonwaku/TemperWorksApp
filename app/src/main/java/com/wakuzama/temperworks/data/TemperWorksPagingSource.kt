package com.wakuzama.temperworks.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wakuzama.temperworks.api.TemperWorksAPI
import com.wakuzama.temperworks.data.other.Resource
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TemperWorksPagingSource(
    private val temperWorksAPI: TemperWorksAPI,
    private val searchQuery: String,
) : PagingSource<Int, TemperWorksItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TemperWorksItem> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = temperWorksAPI.searchPosts(searchQuery)
            val posts = response.data

            LoadResult.Page(
                data = posts,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (posts.isEmpty()) null else position + 1,
            )
        } catch (IOException: IOException) {
            Resource.error("Network error occurred", null)
            LoadResult.Error(IOException)
        } catch (HttpException: HttpException) {
            Resource.error("Http error occurred", null)
            LoadResult.Error(HttpException)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TemperWorksItem>): Int? {
        TODO("Not yet implemented")
    }

}