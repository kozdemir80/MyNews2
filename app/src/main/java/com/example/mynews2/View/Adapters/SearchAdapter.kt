package com.example.mynews2.View.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Model.SearchArticle.Docs
import com.example.mynews2.R
class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchHolder>(){
    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.item_preview,parent,false),mListener
        )
    }
    //Articles which will be displayed in recyclerView
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article=differ.currentList[position]

         holder.view.apply {
             holder.titleView.text = article.section_name
             holder.dView.text = article.headline.main
             holder.date.text = article.pub_date
         }
    }
    //Articles size
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    //Articles views for recyclerView
    class SearchHolder(val view: View,listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date: TextView =view.findViewById(R.id.tvPublishedAt)
        init {

            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
    //DiffUtil for items
    private val differCallBack =object : DiffUtil.ItemCallback<Docs>(){
        override fun areItemsTheSame(oldItem:Docs, newItem:Docs): Boolean {
            return oldItem.web_url== newItem.web_url
        }

        override fun areContentsTheSame(oldItem:Docs, newItem:Docs): Boolean {
            return oldItem==newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallBack)
}




