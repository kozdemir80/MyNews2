package com.example.mynews2.Controller

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

private val CHANNEL_ID= "CHANNEL_ID"
val NOTIFICATION_ID=0
class Notification_Reciever : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val i= Intent(context,Notifications_Result::class.java)
        intent!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent= PendingIntent.getActivity(context,0,i,0)

        val notification= NotificationCompat.Builder(context!!,CHANNEL_ID)
            .setContentTitle("")
            .setContentText("Hello World")
            .setSmallIcon(androidx.core.R.drawable.notification_tile_bg)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager= NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID,notification)

    }
}
