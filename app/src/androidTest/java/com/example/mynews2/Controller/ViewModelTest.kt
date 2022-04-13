package com.example.mynews2.Controller



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.mynews2.Api.Api.MostPopularApi.Repository
import com.example.mynews2.Api.Api.MostPopularApi.RetrofitInstance
import com.example.mynews2.Api.Api.SearchApi.SearchInstance
import com.example.mynews2.Api.Api.SearchApi.SearchRespository


import com.example.mynews2.Api.Api.TopStoriesApi.TopInstance
import com.example.mynews2.Api.Api.TopStoriesApi.TopRepository
import com.example.mynews2.Model.MostPopular.NewsArticle
import com.example.mynews2.Model.SearchArticle.SearchTitle
import com.example.mynews2.Model.TopStories.TopStoriesArticle
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.SearchNewsViewModel


import com.example.mynews2.ViewModel.TopStoriesViewModel
import io.mockk.verifyAll
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock




import retrofit2.Response




@RunWith(AndroidJUnit4::class)
@MediumTest

class ViewModelTest()  {
@Mock
private lateinit var topStoriesViewModel: TopStoriesViewModel
@Mock
private lateinit var topRepository: TopRepository
@Mock
private lateinit var mostPopularViewModel: MostPopularViewModel
@Mock
private lateinit var repository:Repository

@Mock
private lateinit var searchRespository: SearchRespository
@Mock
private lateinit var searchNewsViewModel: SearchNewsViewModel




@get:Rule
var InstantTaskExecutorRule=InstantTaskExecutorRule()

    @Before
    fun topSetUp() {
        topRepository= TopRepository()
        topStoriesViewModel= TopStoriesViewModel(topRepository)
        suspend fun getTopStories(): Response<TopStoriesArticle> {
            return TopInstance.topApi.getTopNews()
        }

        val mResponse: MutableLiveData<Response<TopStoriesArticle>> = MutableLiveData()
      topStoriesViewModel.viewModelScope.launch {
          val response:Response<TopStoriesArticle> = topRepository.getTopStories()
          mResponse.value=response
      }


    }

    @Test
    fun testTopViewModel() {

        topStoriesViewModel= TopStoriesViewModel(repository = topRepository)
        assertNotNull(topStoriesViewModel.getTopNews())
        topStoriesViewModel.mResponse.observeForever {
            verifyAll {
                it.isSuccessful
                it.body()?.results
            }
        }

        topStoriesViewModel.getTopNews()

    }

    @Before
    fun mostPSetUp() {
        repository= Repository()
        mostPopularViewModel= MostPopularViewModel(repository)
        suspend fun getMostPopularNews():Response<NewsArticle> {
            return RetrofitInstance.api.getMostPopularNews()
        }

        val mResponse: MutableLiveData<Response<NewsArticle>> = MutableLiveData()
        mostPopularViewModel.viewModelScope.launch {
            val response:Response<NewsArticle> =repository.getMostPopularNews()
            mResponse.value=response
        }


    }

    @Test
    fun testMostPViewModel(){
        mostPopularViewModel= MostPopularViewModel(repository)
        assertNotNull(mostPopularViewModel.getMostPopularNews())
        mostPopularViewModel.myResponse.observeForever {
            verifyAll {
                it.isSuccessful
                it.body()?.let { mostResponse->
                    mostResponse.results[0].abstract

                }
            }
        }


    }

    @Before
    fun searchViewModelSetUp(){
        searchRespository= SearchRespository()
        searchNewsViewModel= SearchNewsViewModel(searchRespository)
        suspend fun getSearchArticles(
            query: String,
            beginDate: String,
            endDate: String,
            filterQuery: String
        ): Response<SearchTitle> {

            return SearchInstance.api.getArticleSearch(query, beginDate, endDate, filterQuery)
        }
        val searchResponse: MutableLiveData<Response<SearchTitle>> = MutableLiveData()
        fun getSearchNews(query:String,beginDate:String,endDate: String,filterQuery:String)=
           searchNewsViewModel.viewModelScope.launch {
                val response: Response<SearchTitle> = searchRespository.getSearchArticles(query,beginDate,endDate,filterQuery)
                searchResponse.value = response
            }
    }

    @Test

    fun searchViewModelTest(){
        searchNewsViewModel= SearchNewsViewModel(respository = SearchRespository())
        assertNotNull(searchNewsViewModel.getSearchNews("Russia","20220410","20220412","business"))
        searchNewsViewModel.searchResponse.observeForever {
            verifyAll {
                it.isSuccessful
                it.body()?.let { searchResponse->
                    searchResponse.response

                }
            }
        }

    }



}






