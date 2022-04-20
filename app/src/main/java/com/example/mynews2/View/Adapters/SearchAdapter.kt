package com.example.mynews2.View.Adapters








import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup




import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.allViews

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews2.Model.SearchArticle.Docs
import com.example.mynews2.Model.SearchArticle.Multimedia
import com.example.mynews2.Model.SearchArticle.Response

import com.example.mynews2.R




class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.item_preview,parent,false)
        )
    }


    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article=differ.currentList[position]


         holder.view.apply {
             Glide.with(this).load(article.multimedia.firstOrNull()?.url).into(holder.imageView)

             holder.titleView.text = article.section_name
             holder.dView.text = article.headline.main
             holder.date.text = article.pub_date
         }










    }




    private fun it(article:Docs) {

    }



    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    class SearchHolder(val view: View): RecyclerView.ViewHolder(view) {

        val imageView: ImageView = view.findViewById(R.id.ivArticleImage)

        val titleView: TextView = view.findViewById(R.id.tvTitle)
        val dView: TextView = view.findViewById(R.id.tvDescription)
        val date: TextView =view.findViewById(R.id.tvPublishedAt)



    }

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




