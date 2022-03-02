package com.example.mynews2.View.Adapters




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView


import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


import com.example.mynews2.R
import com.example.mynews2.Model.MostPopular.Result
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

       try {


       Glide.with(this).load(article.media[0].mediaMetadata[0].url).into(holder.imageView)}
       catch (e:NullPointerException){}
         holder.titleView.text=article.section
         holder.dView.text= article.title
         holder.date.text=article.published_date




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

    private fun AdapterView.OnItemClickListener.onItemClick(adapterPosition: Int) {

    }

}

