package com.example.mynews2.Controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.Notifications_Adapter
import com.example.mynews2.databinding.SearchItemsBinding
import com.example.mynews2.databinding.SearchNotificationsBinding

class Notification_Activity: AppCompatActivity() {

     private lateinit var binding: SearchNotificationsBinding
     private lateinit var switchCompat: SwitchCompat
     val CHANNEL_ID="channelId"
     val CHANNEL_NAME="channelName"
     val NOTIFICATION_ID=0
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



        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("")
            .setContentText("Hello World")
            .setSmallIcon(androidx.core.R.drawable.notification_tile_bg)
            .build()

        val notificationManager=NotificationManagerCompat.from(this)

        binding.switchNotification.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,notification)
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
        val preferences=getSharedPreferences("checkPreferences", MODE_PRIVATE)
        val editor=preferences.edit()
        binding.checkboxArts1.setOnClickListener {

            it.tag = "arts"
            if (binding.checkboxArts1.isChecked) {
                binding.checkboxArts1.setChecked(true)


                editor.putBoolean("mArts", true)
                editor.apply()
            } else {
                binding.checkboxArts1.setChecked(false)
                editor.putBoolean("mArts", false)
                editor.apply()
            }
        }

        binding.checkboxBusiness1.setOnClickListener {
            it.tag = "business"
            if (binding.checkboxBusiness1.isChecked){
                binding.checkboxBusiness1.setChecked(true)

                editor.putBoolean("mBussiness",true)
                editor.apply()
            }else{
                binding.checkboxBusiness1.setChecked(false)
                editor.putBoolean("mBussiness",false)
                editor.apply()
            }
        }
        binding.checkboxEntrepreneurs1.setOnClickListener {

            it.tag = "entrepreneurs"
            if (binding.checkboxEntrepreneurs1.isChecked){
                binding.checkboxEntrepreneurs1.setChecked(true)

                editor.putBoolean("mEntre",true)
                editor.apply()
            }else{
                binding.checkboxEntrepreneurs1.setChecked(false)
                editor.putBoolean("mEntre",false)
                editor.apply()
            }
        }
        binding.checkboxPolitics1.setOnClickListener {

            it.tag = "politics"
            if (binding.checkboxPolitics1.isChecked){
                binding.checkboxPolitics1.setChecked(true)

                editor.putBoolean("mPolitics",true)
                editor.apply()
            }else{
                binding.checkboxPolitics1.setChecked(false)
                editor.putBoolean("mPolitics",false)
                editor.apply()
            }
        }
        binding.checkboxSports1.setOnClickListener {

            it.tag = "sports"
            if (binding.checkboxSports1.isChecked){
                binding.checkboxSports1.setChecked(true)

                editor.putBoolean("mSports",true)
                editor.apply()
            }else{
                binding.checkboxSports1.setChecked(false)
                editor.putBoolean("mSports",false)
                editor.apply()
            }
        }
        binding.checkboxTravel1.setOnClickListener {

            it.tag = "travel"
            if (binding.checkboxTravel1.isChecked) {
                binding.checkboxTravel1.setChecked(true)

                editor.putBoolean("mTravel", true)
                editor.apply()
            } else {
                binding.checkboxBusiness1.setChecked(false)
                editor.putBoolean("mTravel", false)
                editor.apply()

            }
        }

    }

}


