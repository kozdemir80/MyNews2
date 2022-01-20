package com.example.mynews2.NewsApi

import com.example.mynews2.Constants.Companion.BASE_URL
import com.example.mynews2.Model.NewsArticle
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class RetrofitInstance {

     private val api= Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
         .build()
         .create(NewsApi::class.java)
     fun getData(): Single<List<NewsArticle>> {
        return api.getMostPopularNews()
    }
}
