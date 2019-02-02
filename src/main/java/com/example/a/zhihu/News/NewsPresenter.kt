package com.example.a.zhihu.News

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import com.example.a.zhihu.Data.NewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(var NewsUI : NewsContract.UIView) : NewsContract.Presenter {
    private var model = NewsModel()
    private var date : String? = null
    override fun clearData() {
        date = null
    }

    override fun addData() {
        var call : Call<*>
        if(date == null){
            call = model.getTodayData()
        }else{
            call = model.getBeforeData(date)
        }
        call.enqueue(object : Callback<NewsData> {
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val data = response.body()
                if (data?.getStories() == null) {
                    NewsUI.onNull()
                    Log.d("recyclerData","onNull")

                } else {
                    if (date == null) {
                        NewsUI.addBannerData(data.getTop_stories() as List<NewsData.TopStoriesBean>)
                    }
                    date = data.getDate()
                    NewsUI.addRecyclerData(data.getStories() as List<NewsData.StoriesBean>, date.toString())
                    Log.d("recyclerData","updata")
                }
            }
            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                NewsUI.onError()
                Log.d("recyclerData","onFailure")
            }
        })
    }
}