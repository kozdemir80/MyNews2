package com.example.mynews2.View


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.R
import kotlin.collections.ArrayList


class NewsAdapter(private val newsList:ArrayList<NewsArticle>):RecyclerView.Adapter<NewsAdapter.ArticleNewsHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleNewsHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item_preview,parent,false)
        return ArticleNewsHolder(v)
    }

    override fun onBindViewHolder(holder: ArticleNewsHolder, position: Int) {
       holder.titleView.text=newsList[position].copyright
       holder.dView.text=newsList[position].status

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    class ArticleNewsHolder(val view:View): RecyclerView.ViewHolder(view) {
         val imageView: ImageView = view.findViewById<ImageView>(R.id.ivArticleImage)

         val titleView:TextView= view.findViewById<TextView>(R.id.tvTitle)
         val  dView:TextView=view.findViewById<TextView>(R.id.tvDescription)


    }

}