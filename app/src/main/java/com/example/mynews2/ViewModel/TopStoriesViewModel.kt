package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Model.TopStories.TopStoriesArticle
import com.example.mynews2.Api.Api.TopStoriesApi.TopRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TopStoriesViewModel(private val repository: TopRepository): ViewModel(){
    val mResponse: MutableLiveData<Response<TopStoriesArticle>> = MutableLiveData()

    fun getTopNews(){
        viewModelScope.launch {
            val response:Response<TopStoriesArticle> = repository.getTopStories()
            mResponse.value=response
        }
    }






}