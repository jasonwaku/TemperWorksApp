package com.wakuzama.temperworks.ui.temper_list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.wakuzama.temperworks.data.TemperWorksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TemperListViewModel @Inject constructor(private val repository: TemperWorksRepository, @Assisted state: SavedStateHandle)
    : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    //state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY) can be swapped when filter is enabled

    val posts = currentQuery.switchMap { queryStr ->
        repository.getSearchResults(queryStr).cachedIn(viewModelScope)
    }

    //used in filter query value changes
    fun searchPosts(query: String) {
        currentQuery.value = query
    }

    companion object {
        private val current = LocalDateTime.now()
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val formatted = current.format(formatter).toString()

        private const val CURRENT_QUERY = "current_query"
        private val DEFAULT_QUERY = formatted //"2021-09-06"
    }

}