package com.example.mynews2.Api.Api.Notifications_Api

import com.example.mynews2.Model.Notifications.Notification_Section
import retrofit2.Response

class Notifications_Respository {
    suspend fun getNotifications(
        filterQuery: String,
        query: String
    ): Response<Notification_Section> {

        return Notifications_Instance.api.getNotifications(filterQuery,query)
    }
}