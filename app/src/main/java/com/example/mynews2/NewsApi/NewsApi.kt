package com.example.mynews2.NewsApi

import com.example.mynews2.Model.NewsArticle
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("v2/emailed/{period}.json")



     fun getMostPopularNews():Single<List<NewsArticle>>



}










