package com.example.mynews2.TopStoriesApi

import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.TopStories.TopStoriesArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopApi {

    @GET("topstories/v2/home.json")

    suspend fun getTopNews(
        @Query("api-key")
        apiKey:String= Constants.APİ_KEY
    ): Response<TopStoriesArticle>
}