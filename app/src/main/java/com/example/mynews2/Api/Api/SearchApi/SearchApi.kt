package com.example.mynews2.Api.Api.SearchApi

import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.SearchArticle.SearchTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface SearchApi {

        @GET("search/v2/articlesearch.json")
      suspend fun getArticleSearch(
                @Query("q")
              query: String?,
                @Query("begin_date")
              beginDate:String?,
                @Query("end_date")
              endDate: String?,
                @Query("fq")
              filterQuery: String?,
                @Query("api-key")
              apiKey:String= Constants.APİ_KEY

        ): Response<SearchTitle>
}