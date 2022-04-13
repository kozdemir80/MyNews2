package com.example.mynews2.viewModelTests

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle


import android.text.Editable
import android.text.TextWatcher


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mynews2.R


import com.example.mynews2.databinding.SearchNotificationsBinding

class Notification_Activity: AppCompatActivity() {

     private lateinit var binding: SearchNotificationsBinding
     private lateinit var switchCompat: SwitchCompat
     private val CHANNEL_ID="channelId"
     private val CHANNEL_NAME="channelName"
     private val NOTIFICATION_ID=0
    private var nCategories:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_notifications)
        binding = SearchNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationQuery()
        mNotificationChannel()
        switchCompat=findViewById(R.id.switch_notification)

        val myPreferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
        val editor=myPreferences.edit()

        val intent=Intent(this,Notifications_Result::class.java)
        val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)

        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("")
            .setContentText("Hello World")
            .setSmallIcon(androidx.core.R.drawable.notification_tile_bg)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager=NotificationManagerCompat.from(this)

        binding.switchNotification.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,notification)
            intent.putExtra("nCategories",true)
        }







        binding.queryTerm1.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val queryTerm=binding.queryTerm1.text.toString()
                editor.apply {
                    putString("myQuery",queryTerm)
                }.apply()
            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })


    }

    private fun mNotificationChannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel= NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor= Color.BLACK
                enableLights(true)
            }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun notificationQuery(){
        binding.checkboxArts1.setOnClickListener {

            it.tag = "arts"
            if (binding.checkboxArts1.isChecked) {
                binding.checkboxArts1.setChecked(true)
                 nCategories.add("arts")

            } else {
                binding.checkboxArts1.setChecked(false)
                nCategories.remove("arts")
            }
        }

        binding.checkboxBusiness1.setOnClickListener {
            it.tag = "business"
            if (binding.checkboxBusiness1.isChecked){
                binding.checkboxBusiness1.setChecked(true)
                nCategories.add("business")

            }else{
                binding.checkboxBusiness1.setChecked(false)
                nCategories.remove("business")
            }
        }
        binding.checkboxEntrepreneurs1.setOnClickListener {

            it.tag = "entrepreneurs"
            if (binding.checkboxEntrepreneurs1.isChecked){
                binding.checkboxEntrepreneurs1.setChecked(true)
                nCategories.add("entrepreneurs")

            }else{
                binding.checkboxEntrepreneurs1.setChecked(false)
                nCategories.remove("entrepreneurs")
            }
        }
        binding.checkboxPolitics1.setOnClickListener {

            it.tag = "politics"
            if (binding.checkboxPolitics1.isChecked){
                binding.checkboxPolitics1.setChecked(true)
                nCategories.add("politics")

            }else{
                binding.checkboxPolitics1.setChecked(false)
                nCategories.remove("politics")
            }
        }
        binding.checkboxSports1.setOnClickListener {

            it.tag = "sports"
            if (binding.checkboxSports1.isChecked){
                binding.checkboxSports1.setChecked(true)
                nCategories.add("sports")

            }else{
                binding.checkboxSports1.setChecked(false)
                nCategories.remove("sports")
            }
        }
        binding.checkboxTravel1.setOnClickListener {

            it.tag = "travel"
            if (binding.checkboxTravel1.isChecked) {
                binding.checkboxTravel1.setChecked(true)
                nCategories.add("travel")

            } else {
                binding.checkboxBusiness1.setChecked(false)
                nCategories.remove("travel")

            }
        }

    }

}


