package com.example.mynews2.Api.Api.BusinessApi

import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.Business.BusinessArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
// Interface for Api
interface BusinessApi {
    @GET("topstories/v2/business.json")
    suspend fun getBusinessNews(
        @Query("api-key")
        apiKey:String= Constants.APİ_KEY
    ): Response<BusinessArticle>
}