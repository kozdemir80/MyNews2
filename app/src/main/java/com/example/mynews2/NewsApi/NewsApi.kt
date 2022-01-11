package com.example.mynews2.NewsApi

import com.example.mynews2.Constants.Companion.APİ_KEY
import retrofit2.Response
import retrofit2.http.GET


interface NewsApi {
    @GET("/v2/viewed/{period}.json")


    suspend fun getMostPopularNews(
        @retrofit2.http.Query("country")
        countryCode: String = "us",
        @retrofit2.http.Query("page")
        pageNumber: Int = 1,
        @retrofit2.http.Query("apiKey")
        apiKey:String=APİ_KEY
    ): Response<NewsJson>
}










