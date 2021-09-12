package com.patna.marketplace.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patna.marketplace.repository.BlogApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BloggerViewModel:ViewModel() {

    private val _response = MutableLiveData<String>()
    val response:LiveData<String>
    get() = _response

    init {
        getBloggerBlogPosts()
    }

    fun getBloggerBlogPosts(){
        BlogApi.retrofitService.getBlogPosts().enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure"+t.message
            }

        })
    }
}