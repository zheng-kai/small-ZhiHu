package com.example.a.zhihu.News

import com.example.a.zhihu.Data.Story
import com.example.a.zhihu.Data.TopStory

interface NewsContract {
    interface UIView {
        fun onError()
        fun onNull()
        fun addRecyclerData(stories: List<Story>, date: String)
        fun addBannerData(topStories: List<TopStory>)
        fun refreshData()
    }

    interface Presenter {
        fun addData()
        fun clearData()
    }
}