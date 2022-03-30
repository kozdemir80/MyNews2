package com.example.mynews2.View.Adapters





import android.os.Build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup




import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynews2.Model.SearchArticle.Response

import com.example.mynews2.R




class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.item_preview,parent,false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val article=differ.currentList[position]



         holder.view.apply {
             Glide.with(this).load(article.docs[0].multimedia[0].url).into(holder.imageView)
         }
             holder.titleView.text = article.docs[position].section_name
             holder.dView.text = article.docs[position].lead_paragraph
             holder.date.text = article.docs[position].pub_date











    }




    private fun it(article:Response) {

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

    private val differCallBack =object : DiffUtil.ItemCallback<Response>(){
        override fun areItemsTheSame(oldItem: Response, newItem:Response): Boolean {
            return oldItem.docs== newItem.docs
        }

        override fun areContentsTheSame(oldItem:Response, newItem:Response): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,differCallBack)




}

