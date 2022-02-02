package com.example.mynews2.BussinessApi

import com.example.mynews2.Model.Business.BusinessArticle
import com.example.mynews2.Model.MostPopular.NewsArticle
import com.example.mynews2.NewsApi.MostPopularInstance.RetrofitInstance
import retrofit2.Response

class BusinessRespository {
    suspend fun getBusinessNews(): Response<BusinessArticle> {
        return BusinessInstance.api.getBusinessNews()
    }
}