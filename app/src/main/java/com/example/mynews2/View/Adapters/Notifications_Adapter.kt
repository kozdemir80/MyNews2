package com.example.mynews2.View.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews2.Model.Notifications.Response
import com.example.mynews2.R

class Notifications_Adapter: RecyclerView.Adapter<Notifications_Adapter.NotificationsNewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsNewsHolder {
        return NotificationsNewsHolder(
            LayoutInflater.from(parent.context).inflate
            (R.layout.item_preview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotificationsNewsHolder, position: Int) {
        val article=differ.currentList[position]
        holder.view.apply {

           Glide.with(this).load(article.docs[0].multimedia[0].url).into(holder.imageView)
            holder.titleView.text=article.docs[0].section_name
            holder.dView.text= article.docs[0].lead_paragraph
            holder.date.text=article.docs[0].pub_date




        }


    }

    private fun it(article: com.example.mynews2.Model.Notifications.Response) {

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class NotificationsNewsHolder(val view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivArticleImage)

        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date: TextView =view.findViewById(R.id.tvPublishedAt)




    }

    private val differCallBack =object : DiffUtil.ItemCallback<com.example.mynews2.Model.Notifications.Response>(){
        override fun areItemsTheSame(oldItem:com.example.mynews2.Model.Notifications.Response , newItem: com.example.mynews2.Model.Notifications.Response): Boolean {
            return oldItem.docs == newItem.docs
        }

        override fun areContentsTheSame(oldItem:Response, newItem: Response): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)



}
