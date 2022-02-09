package com.example.mynews2.View.Adapters

import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


import com.example.mynews2.Model.Business.Result
import com.example.mynews2.R
import com.google.android.material.textfield.TextInputEditText



class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.search_notification,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article=differ.currentList[position]


        holder.view.apply {


            setOnClickListener{
                onItemClickListener?.let { it(article) }
            }


        }


    }

    private fun it(article: Result) {

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class SearchHolder(val view: View): RecyclerView.ViewHolder(view) {

        val beginDate=view.findViewById<TextInputEditText>(R.id.begin_date)
        val endDate=view.findViewById<TextInputEditText>(R.id.end_date)
        val button=view.findViewById<Button>(R.id.search_query_button)
        val arts=view.findViewById<CheckBox>(R.id.checkbox_arts)
        val politics=view.findViewById<CheckBox>(R.id.checkbox_politics)
        val business=view.findViewById<CheckBox>(R.id.checkbox_business)
        val sports=view.findViewById<CheckBox>(R.id.checkbox_sports)
        val entrepreneurs=view.findViewById<CheckBox>(R.id.checkbox_entrepreneurs)
        val travel=view.findViewById<CheckBox>(R.id.checkbox_travel)



    }

    private val differCallBack =object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)


    private var onItemClickListener:((Result)-> Unit)?=null

    fun setOnItemClickListen(listener:(Result) -> Unit){
        onItemClickListener =listener
    }


}