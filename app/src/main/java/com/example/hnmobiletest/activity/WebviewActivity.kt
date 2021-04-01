package com.example.hnmobiletest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hnmobiletest.R
import com.example.hnmobiletest.adapter.NewsAdapter
import com.example.hnmobiletest.databinding.ActivityWebviewBinding
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.viewModels.ViewModelFactory
import com.example.hnmobiletest.viewModels.WebViewViewModel

class WebviewActivity : AppCompatActivity() {

    private val vm by lazy {
        ViewModelProvider(this, ViewModelFactory(SessionData.newsData)).get(WebViewViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWebviewBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_webview
        )
        binding.lifecycleOwner = this
        binding.viewmodel = vm

        vm.uiEventValue.observe(binding.lifecycleOwner!!, {
            when (it) {
                1 -> {
                    this.onBackPressed()
                }
            }
        })
    }
}