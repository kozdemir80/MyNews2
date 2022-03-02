package com.example.mynews2.View.Adapters



import android.app.DatePickerDialog
import android.os.Build
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


import com.example.mynews2.Model.Business.Result
import com.example.mynews2.Model.SearchArticle.Response
import com.example.mynews2.Model.SearchArticle.SearchTitle
import com.example.mynews2.R
import com.google.android.material.textfield.TextInputEditText
import retrofit2.http.Query
import java.util.*


class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.search_items,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article=differ.currentList[position]

       holder.arts.isChecked=article.response.docs[0].keywords[0].name[0].isDefined()
        holder.business.isChecked=article.response.docs[0].keywords[0].name[0].isDefined()
        holder.travel.isChecked=article.response.docs[0].keywords[0].name[0].isDefined()
        holder.sports.isChecked=article.response.docs[0].keywords[0].name[0].isDefined()
        holder.politics.isChecked=article.response.docs[0].keywords[0].name[0].isDefined()
        holder.entrepreneurs.isChecked=article.response.docs[0].keywords[0].name[0].isDefined()











    }




    private fun it(article:SearchTitle) {

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class SearchHolder(val view: View): RecyclerView.ViewHolder(view) {

        val beginDate=view.findViewById<TextView>(R.id.begin_date)
        val endDate=view.findViewById<TextInputEditText>(R.id.end_date)
        val button=view.findViewById<Button>(R.id.search_query_button)
        val arts: CheckBox =view.findViewById<CheckBox>(R.id.checkbox_arts)
        val politics=view.findViewById<CheckBox>(R.id.checkbox_politics)
        val business=view.findViewById<CheckBox>(R.id.checkbox_business)
        val sports=view.findViewById<CheckBox>(R.id.checkbox_sports)
        val entrepreneurs=view.findViewById<CheckBox>(R.id.checkbox_entrepreneurs)
        val travel=view.findViewById<CheckBox>(R.id.checkbox_travel)



    }

    private val differCallBack =object : DiffUtil.ItemCallback<SearchTitle>(){
        override fun areItemsTheSame(oldItem: SearchTitle, newItem:SearchTitle): Boolean {
            return oldItem.status == newItem.status
        }

        override fun areContentsTheSame(oldItem:SearchTitle, newItem:SearchTitle): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)


    private var onItemClickListener:((SearchTitle)-> Unit)?=null

    fun setOnItemClickListen(listener:(SearchTitle) -> Unit){
        onItemClickListener =listener
    }


}

