package com.example.mynews2.NewsApi.MostPopularInstance



import com.example.mynews2.Constants.Constants.Companion.APİ_KEY
import com.example.mynews2.Model.MostPopular.NewsArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("mostpopular/v2/emailed/1.json")
    suspend fun getMostPopularNews(
        @Query("api-key")
        apiKey:String=APİ_KEY
    ):Response<NewsArticle>




}












