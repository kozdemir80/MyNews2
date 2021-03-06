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
import com.squareup.picasso.Picasso
import java.lang.NullPointerException
class TopStoriesAdapter :RecyclerView.Adapter<TopStoriesAdapter.NewsHolder>(){
    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return  NewsHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.item_preview,parent,false),mListener
        )
    }
    //Articles which will be displayed in recyclerView
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article=differ.currentList[position]
            try {
        holder.view.apply {
            Picasso.get().load(article.multimedia[0].url_).into(holder.imageView)}
            holder.titleView.text=article.section
            holder.dView.text= article.title
            holder.date.text=article.published_date
        }catch (e:NullPointerException){}
    }
    //Articles size
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    // Article views which will be used in recyclerView
    class NewsHolder(val view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view)
       {
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
       //diffUtil for items
    private val differCallBack =object : DiffUtil.ItemCallback<com.example.mynews2.Model.TopStories.Result>(){
        override fun areItemsTheSame(oldItem: com.example.mynews2.Model.TopStories.Result,
                                     newItem: com.example.mynews2.Model.TopStories.Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: com.example.mynews2.Model.TopStories.Result,
                                        newItem: com.example.mynews2.Model.TopStories.Result): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)
}