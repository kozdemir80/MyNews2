package com.example.mynews2.Controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.MostPopularApi.Repository
import com.example.mynews2.Api.Api.Notifications_Api.Notifications_Respository
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.TopStories.Result
import com.example.mynews2.R
import com.example.mynews2.R.id.switch_notification
import com.example.mynews2.View.Adapters.BusinessAdapter
import com.example.mynews2.View.Adapters.Notifications_Adapter
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.*
import com.example.mynews2.databinding.SearchItemsBinding
import com.example.mynews2.databinding.SearchNotificationsBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Notifications: AppCompatActivity() {
    val CHANNEL_ID="channelId"
    val CHANNEL_NAME="channelName"
    val NOTIFICATİON_İD=0
    private lateinit var adapter:Notifications_Adapter
    private lateinit var switchCompat: SwitchCompat
    private lateinit var binding:SearchNotificationsBinding
    private lateinit var notificationViewModel: NotificationsViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_notifications)
        binding = SearchNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mNotificationChannel()
        switchCompat=findViewById(switch_notification)
        notificationViewModel= NotificationsViewModel(respository = Notifications_Respository())
        adapter= Notifications_Adapter()
        recyclerView= RecyclerView(applicationContext)
        recyclerView.adapter=Notifications_Adapter()
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        adapter.notifyDataSetChanged()


        val repository= Notifications_Respository()
        val NotificationsViewModelFactory=NotificationsViewModelFactory(repository)
        notificationViewModel=ViewModelProvider(this,NotificationsViewModelFactory).get(NotificationsViewModel::class.java)
        notificationViewModel.mGetNotifications()
        notificationViewModel.myResponse.observe(({ lifecycle }) , Observer { response->
            if (response.isSuccessful){
                Log.d("Notification_Response",response.body()?.copyright.toString())
                Log.d("Notification_Response",response.body()?.status.toString())
                Log.d("Notification_Response", response.body()?.num_results.toString())
                Log.d("Notification_Response", response.body()?.results.toString())
                response.body()?.let { newsResponse ->
                    adapter.differ.submitList(newsResponse.results)

                    val notification=NotificationCompat.Builder(this,CHANNEL_ID)
                        .setContentTitle(adapter.differ.currentList[0].title)
                        .setContentText(adapter.differ.currentList[0].abstract)
                        .setSmallIcon(androidx.core.R.drawable.notification_tile_bg)
                        .build()

                    val notificationManager=NotificationManagerCompat.from(this)

                    switchCompat.setOnClickListener {
                        notificationManager.notify(NOTIFICATİON_İD,notification)
                    }
                }
            }else{
                Log.d("nResponse", response.errorBody().toString())
            }

        })




    }

    fun mNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel= NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
               lightColor= Color.BLACK
                enableLights(true)
            }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}