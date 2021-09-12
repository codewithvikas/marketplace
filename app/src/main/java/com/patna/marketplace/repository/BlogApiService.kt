package com.patna.marketplace.repository

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://www.googleapis.com/"
        private const val BLOGGER_PATH =  "blogger/v3/blogs/"
private const val BLOG_ID = 8612537055857812789
private const val POSTS = "/posts/"
private const val QUERY = "fields=items(published,url,title,author/displayName,labels)&key="
private const val BLOG_POSTS_ENDPOINT = BLOGGER_PATH+"${BLOG_ID}"+ POSTS+ QUERY

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BlogApiService{

    @GET(BLOG_POSTS_ENDPOINT)
    fun getBlogPosts(): Call<String>
}

object BlogApi{
    val retrofitService: BlogApiService by lazy {
        retrofit.create(BlogApiService::class.java)
    }
}
