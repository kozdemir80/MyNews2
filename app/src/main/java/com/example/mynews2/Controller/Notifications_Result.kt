package com.example.mynews2.Controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.Notifications_Api.Notifications_Respository
import com.example.mynews2.R
import com.example.mynews2.R.id.switch_notification
import com.example.mynews2.View.Adapters.Notifications_Adapter
import com.example.mynews2.ViewModel.*
import com.example.mynews2.databinding.NotificationDisplayBinding
import com.example.mynews2.databinding.SearchNotificationsBinding

class Notifications_Result: AppCompatActivity() {
    val CHANNEL_ID="channelId"
    val CHANNEL_NAME="channelName"
    val NOTIFICATION_ID=0

    private lateinit var adapter:Notifications_Adapter
    private lateinit var binding: NotificationDisplayBinding
    private lateinit var notificationViewModel: NotificationsViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_display)
        binding = NotificationDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        notificationViewModel= NotificationsViewModel(respository = Notifications_Respository())
        adapter= Notifications_Adapter()
        recyclerView= RecyclerView(applicationContext)
        recyclerView.adapter=Notifications_Adapter()
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
        val preferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
        val mQuery=preferences.getString("myQuery",null)
        val myCategories=intent.getBooleanExtra("nCategorires",true)


        val repository= Notifications_Respository()
        val NotificationsViewModelFactory=NotificationsViewModelFactory(repository)
        notificationViewModel=ViewModelProvider(this,NotificationsViewModelFactory).get(NotificationsViewModel::class.java)
        notificationViewModel.mGetNotifications(query = mQuery.toString(), filterQuery = myCategories.toString())
        notificationViewModel.myResponse.observe(({ lifecycle }) , Observer { response->
            if (response.isSuccessful){
                Log.d("Notification_Response",response.body()?.copyright.toString())
                Log.d("Notification_Response",response.body()?.status.toString())
                Log.d("Notification_Response",response.body()?.response.toString())
                response.body()?.let { newsResponse ->
                    adapter.differ.submitList(newsResponse.response)


                }
            }else{
                Log.d("nResponse", response.errorBody().toString())
            }

        })




    }



}

fun <T> AsyncListDiffer<T>.submitList(response: T) {

}
