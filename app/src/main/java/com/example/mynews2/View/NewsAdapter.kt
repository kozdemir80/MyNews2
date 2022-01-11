package com.example.mynews2.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.R

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val differCallBack = object :DiffUtil.ItemCallback<Article_Fragment>(){
        override fun areItemsTheSame(oldItem: Article_Fragment, newItem: Article_Fragment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article_Fragment, newItem: Article_Fragment): Boolean {
           return  oldItem ==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       return  ArticleViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.article_fragment,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}