package com.example.mynews2.Controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mynews2.R
import com.example.mynews2.R.id.switch_notification
import com.example.mynews2.View.Adapters.BusinessAdapter

class Notifications: AppCompatActivity() {
    val CHANNEL_ID="channelId"
    val CHANNEL_NAME="channelName"
    val NOTIFICATİON_İD=0
    private lateinit var adapter:BusinessAdapter
    private lateinit var switchCompat: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_notifications)
        mNotificationChannel()
        switchCompat=findViewById(R.id.switch_notification)

        adapter= BusinessAdapter()

        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("My News")
            .setContentText(adapter.differ.currentList.size.toString())
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
}