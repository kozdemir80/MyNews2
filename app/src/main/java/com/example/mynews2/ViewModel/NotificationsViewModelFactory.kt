package com.example.mynews2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews2.Api.Api.Notifications_Api.Notifications_Respository

class NotificationsViewModelFactory(private val respository: Notifications_Respository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotificationsViewModel(respository) as T
    }
}