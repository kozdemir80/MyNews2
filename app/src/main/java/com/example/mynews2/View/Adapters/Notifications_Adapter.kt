package com.example.mynews2.View.Adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Model.Notifications.Docs
import com.example.mynews2.R
import java.lang.NullPointerException

class Notifications_Adapter: RecyclerView.Adapter<Notifications_Adapter.NotificationsNewsHolder>() {
    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsNewsHolder {
        return NotificationsNewsHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.item_preview, parent, false), mListener
        )
    }
    //ArticlesViews which will be displayed on recyclerView
    override fun onBindViewHolder(holder: NotificationsNewsHolder, position: Int) {
        val article=differ.currentList[position]

        try {
        holder.view.apply {
            holder.titleView.text=article.section_name
            holder.dView.text= article.headline.main
            holder.date.text=article.pub_date
        }}catch (e:NullPointerException){}
    }
    //Articles size
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
       //Article views for recyclerView
    class NotificationsNewsHolder(val view: View,listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date: TextView =view.findViewById(R.id.tvPublishedAt)

        init {

            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
    private val differCallBack =object : DiffUtil.ItemCallback<Docs>(){
        override fun areItemsTheSame(oldItem:Docs , newItem:Docs): Boolean {
            return oldItem.web_url == newItem.web_url
        }
        override fun areContentsTheSame(oldItem:Docs, newItem:Docs): Boolean {
            return oldItem==newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallBack)
}


