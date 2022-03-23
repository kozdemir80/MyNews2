package com.example.mynews2.Api.Api.Notifications_Api

import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.Notifications.Notifications_Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationsApi{
    @GET("news/v3/content/nyt/all.json")
    suspend fun getNotifications(
        @Query("api-key")
        apiKey:String= Constants.APİ_KEY
    ): Response<Notifications_Article>
}