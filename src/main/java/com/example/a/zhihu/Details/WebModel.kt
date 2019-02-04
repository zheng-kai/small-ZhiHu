package com.example.a.zhihu.Details

import com.example.a.zhihu.Data.DetailData
import com.example.a.zhihu.RetrofitService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebModel {
    var retrofit = Retrofit.Builder().baseUrl("http://news-at.zhihu.com/api/4/news/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service = retrofit.create(RetrofitService::class.java)
    fun getDetailData(id : Int) : Call<DetailData> {
        return service.getDetailData(id)
    }

}