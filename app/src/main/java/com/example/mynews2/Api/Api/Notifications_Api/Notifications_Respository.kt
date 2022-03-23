package com.example.mynews2.Api.Api.Notifications_Api

import com.example.mynews2.Model.Notifications.Notifications_Article
import retrofit2.Response

class Notifications_Respository {
    suspend fun getNotifications(): Response<Notifications_Article> {
        return Notifications_Instance.api.getNotifications()
    }
}