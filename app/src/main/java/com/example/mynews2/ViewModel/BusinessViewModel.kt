package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Api.Api.BusinessApi.BusinessRespository
import com.example.mynews2.Model.Business.BusinessArticle
import kotlinx.coroutines.launch
import retrofit2.Response

class BusinessViewModel(private val respository: BusinessRespository): ViewModel(){
    val myResponse: MutableLiveData<Response<BusinessArticle>> = MutableLiveData()

    fun getMostPopularNews(){
        viewModelScope.launch {
            val response: Response<BusinessArticle> = respository.getBusinessNews()
            myResponse.value=response
        }
    }




}