package com.example.mynews2.NewsApi

import com.example.mynews2.Model.NewsArticle
import retrofit2.Response

class Repository {


    suspend fun getMostPopularNews():Response<NewsArticle> {
      return RetrofitInstance.api.getMostPopularNews()
    }

}