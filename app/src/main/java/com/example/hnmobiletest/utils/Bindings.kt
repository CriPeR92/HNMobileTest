package com.example.hnmobiletest.utils

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.setHasFixedSize(false)
        this.adapter = adapter
        }
}

@BindingAdapter("setWebViewClient")
fun setWebViewClient(view: WebView, client: WebViewClient?) {
    if (client != null) {
        view.webViewClient = client
    }
}

@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String?) {
    view.loadUrl(url.toString())
}

@BindingAdapter("app:hideIfSaved")
fun hideIfSaved(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}