package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Model.MostPopular.NewsArticle


import com.example.mynews2.Api.Api.MostPopularApi.Repository


import kotlinx.coroutines.launch
import retrofit2.Response

class MostPopularViewModel(private val repository: Repository):ViewModel(){
    val myResponse:MutableLiveData<Response<NewsArticle>> = MutableLiveData()

    fun getMostPopularNews(){
        viewModelScope.launch {
            val response: Response<NewsArticle> = repository.getMostPopularNews()
            myResponse.value=response
        }
    }




}