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
import com.example.mynews2.Model.Business.Multimedia
import com.example.mynews2.Model.MostPopular.Result
import com.example.mynews2.R
import java.lang.NullPointerException

class TopStoriesAdapter:RecyclerView.Adapter<TopStoriesAdapter.NewsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.item_preview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article=differ.currentList[position]

        holder.view.apply {
         try {

          Glide.with(this).load(article.multimedia[0].url_).into(holder.imageView)}catch (e:NullPointerException){}
            holder.titleView.text=article.section
            holder.dView.text= article.title
            holder.date.text=article.published_date

            setOnClickListener{
                onItemClickListener?.let { it(article) }
            }


        }


    }

    private fun it(article: com.example.mynews2.Model.TopStories.Result?,multimedia: com.example.mynews2.Model.TopStories.Multimedia) {

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class NewsHolder(val view:View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivArticleImage)

        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date:TextView=view.findViewById(R.id.tvPublishedAt)


    }

    private val differCallBack =object : DiffUtil.ItemCallback<com.example.mynews2.Model.TopStories.Result>(){
        override fun areItemsTheSame(oldItem: com.example.mynews2.Model.TopStories.Result, newItem: com.example.mynews2.Model.TopStories.Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: com.example.mynews2.Model.TopStories.Result, newItem: com.example.mynews2.Model.TopStories.Result): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)


    private var onItemClickListener:((com.example.mynews2.Model.TopStories.Result)-> Unit)?=null

    fun setOnItemClickListen(listener:(com.example.mynews2.Model.TopStories.Result) -> Unit){
        onItemClickListener =listener
    }


}