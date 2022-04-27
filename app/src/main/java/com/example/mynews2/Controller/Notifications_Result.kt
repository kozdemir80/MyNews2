@file:Suppress("NAME_SHADOWING")

package com.example.mynews2.Controller



import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.Notifications_Api.Notifications_Respository
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.Notifications_Adapter
import com.example.mynews2.ViewModel.*
import com.example.mynews2.databinding.NotificationDisplayBinding
import com.example.mynews2.databinding.SearchNotificationsBinding

class Notifications_Result: AppCompatActivity() {
    private lateinit var adapter:Notifications_Adapter
    private lateinit var binding: NotificationDisplayBinding
    private lateinit var notificationViewModel: NotificationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_notifications)
        binding = NotificationDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter= Notifications_Adapter()
       binding.notificationRecyclerView.findViewById<RecyclerView>(R.id.notification_recycler_view)
       binding.notificationRecyclerView.adapter=adapter
       binding.notificationRecyclerView.layoutManager=LinearLayoutManager(this)
       binding.notificationRecyclerView.setHasFixedSize(true)

        val preferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
        val mQuery=preferences.getString("myQuery",null)
        val myCategories=intent.getBooleanExtra("nCategories",true)
        notificationViewModel= NotificationsViewModel(respository = Notifications_Respository())
        val repository= Notifications_Respository()
        val notificationsViewModelFactory=NotificationsViewModelFactory(repository)
        notificationViewModel=ViewModelProvider(this,notificationsViewModelFactory).get(NotificationsViewModel::class.java)
        notificationViewModel.mGetNotifications(query = mQuery.toString(), filterQuery = myCategories.toString())
        notificationViewModel.myResponse.observe(({ lifecycle })) { response ->
            if (response.isSuccessful) {
                Log.d("Notification_Response", response.body()?.copyright.toString())
                Log.d("Notification_Response", response.body()?.status.toString())
                Log.d("Notification_Response", response.body()?.response.toString())
                response.body()?.let { newsResponse ->
                    adapter.differ.submitList(newsResponse.response.docs)
                    adapter.setOnItemClickListener(object :
                        Notifications_Adapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val preferences =
                                getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
                            val editor = preferences.edit()
                            editor.putString("mSearch",
                                newsResponse.response.docs[position].web_url)
                            editor.apply()

                            val intent =
                                Intent(this@Notifications_Result, searchWebView::class.java)
                            startActivity(intent)
                        }
                    })
                }
            } else {
                Log.d("nResponse", response.errorBody().toString())
            }
        }
    }
}










