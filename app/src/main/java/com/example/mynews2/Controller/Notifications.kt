package com.example.mynews2.Controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.widget.addTextChangedListener
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Constants.Constants
import com.example.mynews2.Model.TopStories.Result
import com.example.mynews2.R
import com.example.mynews2.R.id.switch_notification
import com.example.mynews2.View.Adapters.BusinessAdapter
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.databinding.SearchItemsBinding
import com.example.mynews2.databinding.SearchNotificationsBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Notifications: AppCompatActivity(), View.OnClickListener {
    val CHANNEL_ID="channelId"
    val CHANNEL_NAME="channelName"
    val NOTIFICATİON_İD=0
    private lateinit var adapter:SearchAdapter
    private lateinit var switchCompat: SwitchCompat
    private lateinit var searchNewsViewModel:SearchNewsViewModel
    private lateinit var binding:SearchNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_notifications)
        binding = SearchNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mNotificationChannel()
        switchCompat=findViewById(switch_notification)
         searchNewsViewModel= SearchNewsViewModel(respository = SearchRespository())
        adapter= SearchAdapter()




        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("My News")
            .setContentText("Heyo!!")
            .setSmallIcon(androidx.core.R.drawable.notification_tile_bg)
            .build()

        val notificationManager=NotificationManagerCompat.from(this)
        switchCompat.setOnClickListener {
            notificationManager.notify(NOTIFICATİON_İD,notification)
        }

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
    override fun onClick(p0: View?) {
        binding.checkboxArts1.let {
            it.setOnClickListener(this)
            it.tag = "arts"

        }

        binding.checkboxBusiness1.let {
            it.setOnClickListener(this)
            it.tag = "business"
        }
        binding.checkboxEntrepreneurs1.let {
            it.setOnClickListener(this)
            it.tag = "entrepreneurs"
        }
        binding.checkboxPolitics1.let {
            it.setOnClickListener(this)
            it.tag = "politics"
        }
        binding.checkboxSports1.let {
            it.setOnClickListener(this)
            it.tag = "sports"
        }
        binding.checkboxTravel1.let {
            it.setOnClickListener(this)
            it.tag = "travel"
        }


    }
}