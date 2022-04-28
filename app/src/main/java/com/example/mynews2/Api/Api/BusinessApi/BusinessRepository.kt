package com.example.mynews2.Api.Api.BusinessApi
import com.example.mynews2.Model.Business.BusinessArticle
import retrofit2.Response

//to provide a clean API for accessing data
class BusinessRepository {
    suspend fun getBusinessNews(): Response<BusinessArticle> {
        return BusinessInstance.api.getBusinessNews()
    }
}