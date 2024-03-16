package com.example.supobasetesting.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supobasetesting.Common.News
import com.example.supobasetesting.R

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var news = emptyList<News>()
    class NewsViewHolder (view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  news.count()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.titleTextView).text = news[position].title
            findViewById<TextView>(R.id.contentTextView).text = news[position].text
        }
    }
}