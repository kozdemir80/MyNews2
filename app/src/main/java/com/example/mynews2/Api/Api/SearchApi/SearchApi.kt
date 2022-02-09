package com.example.mynews2.Api.Api.SearchApi

import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.ArticleSearch.SearchTitle
import com.example.mynews2.Model.Business.BusinessArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/v2/articlesearch.json")

    suspend fun getSearchArticles(
        @Query("api-key")
        apiKey:String= Constants.APİ_KEY
    ): Response<SearchTitle>
}