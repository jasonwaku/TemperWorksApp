package com.wakuzama.temperworks.ui.temper_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.wakuzama.temperworks.api.TemperWorksAPI
import com.wakuzama.temperworks.data.TemperWorksItem
import com.wakuzama.temperworks.data.TemperWorksRepositoryTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TemperListViewModelTest{

    private lateinit var viewModel: TemperListViewModel
    private lateinit var temperApi: TemperWorksAPI
    private val state: SavedStateHandle = SavedStateHandle()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiHelper: TemperWorksAPI

    @Mock
    private lateinit var apiUsersObserver: Observer<PagingData<TemperWorksItem>>


    @Before
    fun setUp(){
        viewModel = TemperListViewModel(TemperWorksRepositoryTest(temperApi), state)
    }

    @Test
    suspend fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
//        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<TemperWorksItem>())
                .`when`(apiHelper).searchPosts("2021-09-09")
            val viewModelT = TemperListViewModel(TemperWorksRepositoryTest(temperApi), state)
            viewModelT.posts.observeForever(apiUsersObserver)
            verify(apiHelper).searchPosts("2021-09-09")
//        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}
