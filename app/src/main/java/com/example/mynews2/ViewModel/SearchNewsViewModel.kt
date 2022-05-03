package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Model.SearchArticle.SearchTitle
import kotlinx.coroutines.launch
import retrofit2.Response
class SearchNewsViewModel(private val respository: SearchRespository):ViewModel() {
    val searchResponse: MutableLiveData<Response<SearchTitle>> = MutableLiveData()
    fun getSearchNews(query:String, beginDate:String, endDate: String, filterQuery:String)=
        viewModelScope.launch {
            val response: Response<SearchTitle> = respository.getSearchArticles(query,beginDate,endDate,filterQuery)
            searchResponse.value = response
        }

}