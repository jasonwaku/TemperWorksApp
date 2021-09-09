package com.wakuzama.temperworks.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.wakuzama.temperworks.api.TemperWorksAPI
import org.junit.Assert.*

class TemperWorksRepositoryTest(temperWorksAPI: TemperWorksAPI) :
    TemperWorksRepository(temperWorksAPI) {

    private val temperItems = mutableListOf<TemperWorksItem>() as PagingData<TemperWorksItem>
    private val observableTemperItems = MutableLiveData(temperItems)

    private var shouldNetworkError = false
    fun setShouldNetworkError(value: Boolean){
        shouldNetworkError = value
    }

    override fun getSearchResults(query: String): LiveData<PagingData<TemperWorksItem>> {
        return observableTemperItems
    }
}