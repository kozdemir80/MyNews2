package com.example.mynews2.View.Adapters





import android.os.Build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView


import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews2.Model.SearchArticle.SearchTitle
import com.example.mynews2.R
import java.lang.NullPointerException


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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article=differ.currentList[position]
         try {


         holder.view.apply {
             Glide.with(this).load(article.response.docs[0].multimedia[0].url).into(holder.imageView)
             holder.titleView.text = article.response.docs[0].section_name
             holder.dView.text = article.response.docs[0].abstract
             holder.date.text = article.response.docs[0].pub_date

         }}catch (e:NullPointerException){}











    }




    private fun it(article:SearchTitle) {

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class SearchHolder(val view: View,listener: onItemClickListener): RecyclerView.ViewHolder(view) {

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

    private val differCallBack =object : DiffUtil.ItemCallback<SearchTitle>(){
        override fun areItemsTheSame(oldItem: SearchTitle, newItem:SearchTitle): Boolean {
            return oldItem.response== newItem.response
        }

        override fun areContentsTheSame(oldItem:SearchTitle, newItem:SearchTitle): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)


    private fun AdapterView.OnItemClickListener.onItemClick(adapterPosition: Int) {

    }

}

