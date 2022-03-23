package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Api.Api.Notifications_Api.Notifications_Respository
import com.example.mynews2.Model.Notifications.Notifications_Article
import kotlinx.coroutines.launch
import retrofit2.Response

class NotificationsViewModel(private val respository: Notifications_Respository):ViewModel(){
    val myResponse: MutableLiveData<Response<Notifications_Article>> = MutableLiveData()

    fun mGetNotifications(){
        viewModelScope.launch {
            val response: Response<Notifications_Article> = respository.getNotifications()
           myResponse.value=response
        }
    }
}