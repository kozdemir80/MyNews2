package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Model.SearchArticle.SearchTitle
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class SearchNewsViewModel(private val respository: SearchRespository):ViewModel() {
    val searchResponse: MutableLiveData<Response<SearchTitle>> = MutableLiveData()
    fun getSearchNews()=
        viewModelScope.launch {
            val response: Response<SearchTitle> = respository.getSearchArticles(query = String, beginDate = Date(), endDate = Date(), filterQuery = String)
            searchResponse.value = response
        }

}