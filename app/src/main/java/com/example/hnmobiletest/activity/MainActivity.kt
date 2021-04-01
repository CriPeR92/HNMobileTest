package com.example.hnmobiletest.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hnmobiletest.R
import com.example.hnmobiletest.adapter.NewsAdapter
import com.example.hnmobiletest.databinding.ActivityMainBinding
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.viewModels.NewsViewModel


class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private val vm by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding  = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewmodel = vm

        vm.randomUserResponse.observe(binding.lifecycleOwner!!, {
            vm.uiEventValue.value = 3
            vm.isLoading.set(false)
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
                    SessionData.isLoading = false
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
}