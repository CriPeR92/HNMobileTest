package com.example.hnmobiletest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hnmobiletest.models.NewsResponse
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.utils.retrofit.APIClient
import com.example.hnmobiletest.utils.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.Comparator


object NewsMobileRepository {

    var apiInterface: APIInterface? = null
    private val randomUserResponse = MutableLiveData<NewsResponse>()

    fun getRandomUsers(): MutableLiveData<NewsResponse> {

        SessionData.isLoading = true
        apiInterface = APIClient.getClient()?.create(APIInterface::class.java)
        val call = apiInterface!!.getLatestNews()
        call.enqueue(object : Callback<NewsResponse> {
            @Override
            override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>,
            ) {
                val usersResponse = response.body()

                usersResponse?.let {
                    randomUserResponse.value = it
                }
                if (SessionData.news.size == 0) {
                    randomUserResponse.value!!.hits.sortWith(Comparator { o1, o2 -> o1.created_at!!.compareTo(o2.created_at!!) })
                    for (i in randomUserResponse.value!!.hits) {
                        SessionData.news.add(i)
                    }
                }

            }

            @Override
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ERROR", t.toString());
            }
        })

        return randomUserResponse
    }
}