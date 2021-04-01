package com.example.hnmobiletest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hnmobiletest.R
import com.example.hnmobiletest.adapter.NewsAdapter
import com.example.hnmobiletest.data.News
import com.example.hnmobiletest.data.NewsRoomViewModel
import com.example.hnmobiletest.databinding.ActivityMainBinding
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.viewModels.NewsViewModel


class MainActivity : AppCompatActivity() {

    var newsList: List<News>? = null
    lateinit var adapter: NewsAdapter
    private val vm by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    private lateinit var newsRoomViewModel: NewsRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding  = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewmodel = vm

        vm.newsResponse.observe(binding.lifecycleOwner!!, {
            if (SessionData.noInternet) {
                loadOldNews()
                SessionData.noInternet = false
            } else {
                vm.uiEventValue.value = 3
                vm.isLoading.set(false)
            }
        })

        vm.uiEventValue.observe(binding.lifecycleOwner!!, {
            when (it) {
                1 -> {
                    val myIntent = Intent(this, WebviewActivity::class.java)
                    this.startActivity(myIntent)
                }
                2 -> {
                    adapter.notifyDataSetChanged()
                }
                3 -> {
                    if (::adapter.isInitialized) {
                        adapter.notifyDataSetChanged()
                    } else {
                        adapter = NewsAdapter(this)
                        binding.adapter = adapter
                    }
                }
            }
        })
        adapter = NewsAdapter(this)
        binding.adapter = adapter

    }

    private fun loadOldNews() {
        newsRoomViewModel = ViewModelProvider(this).get(NewsRoomViewModel::class.java)
        newsRoomViewModel.readAllData.observe(this, { news ->
            newsList = news
            if (!news.isNullOrEmpty()) {
                SessionData.news = news as MutableList<News>
                adapter.notifyDataSetChanged()
            }
        })
    }
}