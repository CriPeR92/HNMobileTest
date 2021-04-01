package com.example.hnmobiletest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.hnmobiletest.activity.MainActivity
import com.example.hnmobiletest.databinding.ItemNewsBinding
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.viewModels.NewsViewModel


class NewsAdapter(var activity: MainActivity) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var vm: NewsViewModel
    private val viewBinderHelper = ViewBinderHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        vm = ViewModelProvider(activity).get(NewsViewModel::class.java)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.news = SessionData.news[position]

//        viewBinderHelper.bind(holder.binding.root.swip, SessionData.news[position].objectID);

    }

    override fun getItemCount(): Int {
        return SessionData.news.size
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}