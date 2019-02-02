package com.example.a.zhihu

import com.example.a.zhihu.Data.DetailData
import com.example.a.zhihu.Data.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("latest")
    abstract fun getTodayData(): Call<NewsData>

    @GET("before/{data}")
    abstract fun getBeforeData(@Path("data") data: String?): Call<NewsData>

    @GET("{id}")
    abstract fun getDetailData(@Path("id") id: Int): Call<DetailData>
}