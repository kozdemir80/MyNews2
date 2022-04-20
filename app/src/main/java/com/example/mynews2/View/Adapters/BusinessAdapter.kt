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
import com.example.mynews2.Model.Business.Result
import com.example.mynews2.R


import kotlin.NullPointerException

class BusinessAdapter: RecyclerView.Adapter<BusinessAdapter.BusinessNewsHolder>(){

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{



        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClickListener) {
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessNewsHolder {
        return BusinessNewsHolder(
            LayoutInflater.from(parent.context).inflate
            (R.layout.item_preview,parent,false),mListener
        )
    }

    override fun onBindViewHolder(holder: BusinessNewsHolder, position: Int) {
        val article=differ.currentList[position]
        holder.view.apply {
            try {

            Glide.with(this).load(article.multimedia[0].url).into(holder.imageView)}
            catch (e:NullPointerException){}
            holder.titleView.text=article.section
            holder.dView.text= article.title
            holder.date.text=article.published_date



        }


    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class BusinessNewsHolder(val view: View,listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivArticleImage)

        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date: TextView =view.findViewById(R.id.tvPublishedAt)

        init {

            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }

    }

    private val differCallBack =object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem:Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem:Result, newItem:Result): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)





}