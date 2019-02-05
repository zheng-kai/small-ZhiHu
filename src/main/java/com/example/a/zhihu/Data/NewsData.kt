package com.example.a.zhihu.Data

data class NewsData(
    var date: String,
    var stories: List<Story>,
    var top_stories: List<TopStory>
)

data class TopStory(
    var ga_prefix: String,
    var id: Int,
    var image: String,
    var title: String,
    var type: Int
)

data class Story(
    var ga_prefix: String,
    var id: Int,
    var images: List<String>,
    var title: String,
    var type: Int
)