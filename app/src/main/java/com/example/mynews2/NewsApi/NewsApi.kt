package com.example.mynews2.NewsApi

import com.example.mynews2.Constants.Companion.APİ_KEY
import com.example.mynews2.Model.Media
import com.example.mynews2.Model.MediaMetadata
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.Model.Result
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface NewsApi {

    @GET("mostpopular/v2/emailed/1.json")

    suspend fun getMostPopularNews(
        @Query("api-key")
        apiKey:String=APİ_KEY
    ):NewsArticle

}










