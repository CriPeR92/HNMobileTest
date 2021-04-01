package com.example.hnmobiletest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.hnmobiletest.activity.MainActivity
import com.example.hnmobiletest.data.NewsRoomViewModel
import com.example.hnmobiletest.databinding.ItemNewsBinding
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.viewModels.NewsViewModel


class NewsAdapter(var activity: MainActivity) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var vm: NewsViewModel
    private val viewBinderHelper = ViewBinderHelper()
    private lateinit var newsRoomViewModel: NewsRoomViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        vm = ViewModelProvider(activity).get(NewsViewModel::class.java)
        newsRoomViewModel = ViewModelProvider(activity).get(NewsRoomViewModel::class.java)
        viewBinderHelper.setOpenOnlyOne(true)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.news = SessionData.news[position]
        newsRoomViewModel.addNews(SessionData.news[position])
    }

    override fun getItemCount(): Int {
        return SessionData.news.size
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}