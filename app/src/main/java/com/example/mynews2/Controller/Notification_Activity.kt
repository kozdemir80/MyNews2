package com.example.mynews2.Controller

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.mynews2.R
import com.example.mynews2.databinding.SearchNotificationsBinding
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NAME_SHADOWING")
class Notification_Activity: AppCompatActivity() {
     val CHANNEL_ID="channelId"
     val CHANNEL_NAME="channelName"
     val NOTIFICATION_ID=0
     private lateinit var binding: SearchNotificationsBinding
     private lateinit var switchCompat: SwitchCompat
     private var nCategories:ArrayList<String> = ArrayList()
    private lateinit var calendar: Calendar
     private lateinit var alarmManager:android.app.AlarmManager


    @SuppressLint("UnspecifiedImmutableFlag", "ObsoleteSdkInt", "ServiceCast",
        "LaunchActivityFromNotification")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_notifications)
        binding = SearchNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationQuery()
        switchCompat=findViewById(R.id.switch_notification)
        setAlarm()

        val myPreferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
        val editor=myPreferences.edit()
         // switch button
        binding.switchNotification.setOnClickListener {

            intent.putExtra("nCategories",true)
            setAlarm()
            savedStateRegistry
        }
        binding.queryTerm1.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val queryTerm=binding.queryTerm1.text.toString()
                editor.apply {
                    putString("myQuery",queryTerm)
                }.apply()
                savedStateRegistry
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
     // setting alarm for broadcast receiver
    private fun setAlarm() {
        calendar= Calendar.getInstance()
        calendar[Calendar.DAY_OF_WEEK]
        calendar[Calendar.HOUR_OF_DAY]
        calendar[Calendar.SECOND]
        calendar[Calendar.MILLISECOND]
        alarmManager=getSystemService(ALARM_SERVICE) as android.app.AlarmManager
        val intent=Intent(this,AlarmManager::class.java)
        val pendingIntent=PendingIntent.getBroadcast(
            this,
            0,
            intent,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.setRepeating(android.app.AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
        android.app.AlarmManager.INTERVAL_DAY,pendingIntent)
        savedStateRegistry
    }
     // checkBoxes for user selections
    private fun notificationQuery(){
        binding.checkboxArts1.setOnClickListener {

            it.tag = "arts"
            if (binding.checkboxArts1.isChecked) {
                binding.checkboxArts1.isChecked = true
                nCategories.add("arts")
                savedStateRegistry

            } else {
                binding.checkboxArts1.isChecked = false
                nCategories.remove("arts")
                savedStateRegistry
            }
        }

        binding.checkboxBusiness1.setOnClickListener {
            it.tag = "business"
            if (binding.checkboxBusiness1.isChecked){
                binding.checkboxBusiness1.isChecked = true
                nCategories.add("business")
                savedStateRegistry

            }else{
                binding.checkboxBusiness1.isChecked = false
                nCategories.remove("business")
                savedStateRegistry
            }
        }
        binding.checkboxEntrepreneurs1.setOnClickListener {

            it.tag = "entrepreneurs"
            if (binding.checkboxEntrepreneurs1.isChecked){
                binding.checkboxEntrepreneurs1.isChecked = true
                nCategories.add("entrepreneurs")
                savedStateRegistry

            }else{
                binding.checkboxEntrepreneurs1.isChecked = false
                nCategories.remove("entrepreneurs")
                savedStateRegistry
            }
        }
        binding.checkboxPolitics1.setOnClickListener {

            it.tag = "politics"
            if (binding.checkboxPolitics1.isChecked){
                binding.checkboxPolitics1.isChecked = true
                nCategories.add("politics")
                savedStateRegistry

            }else{
                binding.checkboxPolitics1.isChecked = false
                nCategories.remove("politics")
                savedStateRegistry
            }
        }
        binding.checkboxSports1.setOnClickListener {

            it.tag = "sports"
            if (binding.checkboxSports1.isChecked){
                binding.checkboxSports1.isChecked = true
                nCategories.add("sports")
                savedStateRegistry

            }else{
                binding.checkboxSports1.isChecked = false
                nCategories.remove("sports")
                savedStateRegistry
            }
        }
        binding.checkboxTravel1.setOnClickListener {

            it.tag = "travel"
            if (binding.checkboxTravel1.isChecked) {
                binding.checkboxTravel1.isChecked = true
                nCategories.add("travel")
                savedStateRegistry

            } else {
                binding.checkboxBusiness1.isChecked = false
                nCategories.remove("travel")
                savedStateRegistry


            }
        }
    }
}
