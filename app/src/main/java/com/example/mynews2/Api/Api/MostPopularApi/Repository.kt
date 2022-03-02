package com.example.mynews2.Api.Api.MostPopularApi

import com.example.mynews2.Model.MostPopular.NewsArticle
import retrofit2.Response

class Repository{


    suspend fun getMostPopularNews():Response<NewsArticle> {
        return RetrofitInstance.api.getMostPopularNews()
    }


}