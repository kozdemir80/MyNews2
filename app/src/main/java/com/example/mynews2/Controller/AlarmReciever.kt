@file:Suppress("NAME_SHADOWING")

package com.example.mynews2.Controller

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mynews2.R
private const val CHANNEL_ID="channelId"
private const val CHANNEL_NAME="channelName"
private const val NOTIFICATION_ID=0
class AlarmManager:BroadcastReceiver(){
    @SuppressLint("ObsoleteSdkInt", "UnspecifiedImmutableFlag", "ResourceAsColor")
    override fun onReceive(context: Context?, intent: Intent?) {
        val intent=Intent(context,Notifications_Result::class.java)

        val pendingIntent= PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //setting notifications
        val notification= context?.let {
            NotificationCompat.Builder(it,CHANNEL_ID)
                .setContentText("You have new articles available")
                .setSmallIcon(R.mipmap.ic_articles)
                .setColor(R.color.white)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()
        }

        val notificationManager= NotificationManagerCompat.from(context!!)
        if (notification != null) {
            notificationManager.notify(NOTIFICATION_ID,notification)
        }
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH).apply {
                enableLights(true)
            }
            val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, notification!!)
    }
}