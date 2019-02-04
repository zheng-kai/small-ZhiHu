package com.example.a.zhihu.News

import com.example.a.zhihu.Data.NewsData
import com.example.a.zhihu.R.id.date
import com.example.a.zhihu.RetrofitService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsModel {
    private var retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://news-at.zhihu.com/api/4/news/")
        .build()
    private var service = retrofit.create(RetrofitService::class.java)

    fun getTodayData() : Call<NewsData> {
        return service.getTodayData()
    }
    fun getBeforeData(date : String?) : Call<NewsData>{
        return service.getBeforeData(date)
    }
}