package com.wakuzama.temperworks.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.wakuzama.temperworks.api.TemperWorksAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class TemperWorksRepository @Inject constructor(private val temperWorksAPI: TemperWorksAPI) {

    open fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {TemperWorksPagingSource(temperWorksAPI, query)}
        ).liveData
}