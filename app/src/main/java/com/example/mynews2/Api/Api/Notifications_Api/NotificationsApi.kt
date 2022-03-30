package com.example.mynews2.Api.Api.Notifications_Api

import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.Notifications.Notification_Section
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationsApi{
    @GET("search/v2/articlesearch.json")
    suspend fun getNotifications(
        @Query("q")
        query: String?,
        @Query("fq")
        filterQuery: String?,
        @Query("api-key")
        apiKey:String= Constants.APİ_KEY
    ): Response<Notification_Section>
}