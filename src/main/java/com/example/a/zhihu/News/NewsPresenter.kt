package com.example.a.zhihu.News

import android.util.Log
import com.example.a.zhihu.Data.NewsData
import com.example.a.zhihu.Data.Story
import com.example.a.zhihu.Data.TopStory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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
                if (data?.stories == null) {
                    NewsUI.onNull()
                    Log.d("recyclerData","onNull")

                } else {

                    if (date == null) {
                        NewsUI.addBannerData(data.top_stories)
                    }
                    date = data.date
                    NewsUI.addRecyclerData(data.stories , date.toString())
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