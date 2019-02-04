package com.example.a.zhihu.News

import com.example.a.zhihu.Data.NewsData

interface NewsContract {
    interface UIView {
        fun onError()
        fun onNull()
        fun addRecyclerData(stories: List<NewsData.StoriesBean>, date: String)
        fun addBannerData(topStories: List<NewsData.TopStoriesBean>)
        fun refreshData()
    }

    interface Presenter {
        fun addData()
        fun clearData()
    }
}