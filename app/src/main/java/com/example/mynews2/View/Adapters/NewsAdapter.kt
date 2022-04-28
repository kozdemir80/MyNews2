package com.example.mynews2.View.Adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.R
import com.example.mynews2.Model.MostPopular.Result
import com.squareup.picasso.Picasso
import java.lang.NullPointerException
class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleNewsHolder>(){
    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleNewsHolder {
        return ArticleNewsHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.item_preview,parent,false),mListener
        )
    }
    // ArticlesViews which will be displayed on recyclerView
    override fun onBindViewHolder(holder: ArticleNewsHolder, position: Int) {
        val article=differ.currentList[position]
       holder.view.apply {
       try {
         Picasso.get().load(article.media[0].mediaMetadata[0].url).into(holder.imageView)}
       catch (e:NullPointerException){}
         holder.titleView.text=article.section
         holder.dView.text= article.title
         holder.date.text=article.published_date
       }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    // Articles views which will be used in recyclerview
    class ArticleNewsHolder(val view:View,listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivArticleImage)
        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date:TextView=view.findViewById(R.id.tvPublishedAt)
        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
    // diffUtil for items
    private val differCallBack =object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallBack)
}

