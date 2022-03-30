package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Api.Api.Notifications_Api.Notifications_Respository
import com.example.mynews2.Model.Notifications.Notification_Section
import kotlinx.coroutines.launch
import retrofit2.Response

class NotificationsViewModel(private val respository: Notifications_Respository):ViewModel(){
    val myResponse: MutableLiveData<Response<Notification_Section>> = MutableLiveData()

    fun mGetNotifications(query:String,filterQuery:String){
        viewModelScope.launch {
            val response: Response<Notification_Section> = respository.getNotifications(query,filterQuery)
           myResponse.value=response
        }
    }
}