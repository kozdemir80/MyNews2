package com.example.mynews2.View.Adapters


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews2.Model.MostPopular.Media
import com.example.mynews2.Model.MostPopular.MediaMetadata
import com.example.mynews2.Model.MostPopular.NewsArticle
import com.example.mynews2.R
import com.example.mynews2.Model.MostPopular.Result
import retrofit2.http.Url

import java.lang.NullPointerException


class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleNewsHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleNewsHolder {
        return ArticleNewsHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.item_preview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ArticleNewsHolder, position: Int) {
        val article=differ.currentList[position]
       holder.view.apply {


       Glide.with(this).load(article.media.get(0).mediaMetadata.get(0).url).into(holder.imageView)
         holder.titleView.text=article.section
         holder.dView.text= article.title
         holder.date.text=article.published_date

           setOnClickListener {
               onItemClickListener?.let { it(article) }
               val intent=Intent(Intent.ACTION_VIEW)
               intent.data= Uri.parse(article.url[position].toString())
               startActivity(holder.view.context,intent,null)
           }


       }


    }

    private fun it(article:Result?) {

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class ArticleNewsHolder(val view:View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivArticleImage)

        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date:TextView=view.findViewById(R.id.tvPublishedAt)




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


    private var onItemClickListener:((NewsArticle)-> Unit)?=null

    fun setOnItemClickListen(listener:(NewsArticle) -> Unit){
        onItemClickListener =listener
    }
}

