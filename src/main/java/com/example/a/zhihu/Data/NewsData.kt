package com.example.a.zhihu.Data

class NewsData {
    /**
     * date : 20181126
     * stories : [{"images":["https://pic2.zhimg.com/v2-fa459d5f13d04ffecbaad27b9db633f5.jpg"],"type":0,"id":9702404,"ga_prefix":"112615","title":"一个坏消息：世界首例基因编辑婴儿在中国诞生"},{"images":["https://pic1.zhimg.com/v2-d24f19de9b73c4fb2f9472993179f6f4.jpg"],"type":0,"id":9702399,"ga_prefix":"112613","title":"「白水变牛奶」的视频有点可怕，市面上的奶是不是都这么来的？"},{"images":["https://pic4.zhimg.com/v2-1649c69f8e2b1d2eea78620ce52cd39b.jpg"],"type":0,"id":9702335,"ga_prefix":"112612","title":"大误 · 我是不是学了假英语"},{"images":["https://pic1.zhimg.com/v2-ab09a8bbdd4bf07f15b5c99f760fd514.jpg"],"type":0,"id":9702324,"ga_prefix":"112610","title":"在实验室绽放的爱情"},{"title":"一幅画就是一个，不，好多个故事","ga_prefix":"112609","images":["https://pic1.zhimg.com/v2-7599bfccdbfc45acf42d7ccff06cddfc.jpg"],"multipic":true,"type":0,"id":9701854},{"images":["https://pic2.zhimg.com/v2-2cc91b27f39f27a5c3ee1661b3470f29.jpg"],"type":0,"id":9702363,"ga_prefix":"112608","title":"工蜂一直这么勤勤恳恳，不会起兵造反吗？"},{"images":["https://pic3.zhimg.com/v2-9d7519473c98e44cb55a99c0316b776a.jpg"],"type":0,"id":9702366,"ga_prefix":"112607","title":"英国「租借」香港 99 年，其实 99 年和 999 年，差别不大"},{"images":["https://pic4.zhimg.com/v2-b4b995914d883fa63a7721952ccd4e7f.jpg"],"type":0,"id":9702374,"ga_prefix":"112607","title":"「鱼缸」里的京东"},{"images":["https://pic1.zhimg.com/v2-0a8e52419ff7e23932a9fe669bdafad8.jpg"],"type":0,"id":9702347,"ga_prefix":"112606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-05c2d2850902a8f03f1835f787c0c55d.jpg","type":0,"id":9702404,"ga_prefix":"112615","title":"一个坏消息：世界首例基因编辑婴儿在中国诞生"},{"image":"https://pic2.zhimg.com/v2-f24506793034bcf7b2845865d2e95fa5.jpg","type":0,"id":9702374,"ga_prefix":"112607","title":"「鱼缸」里的京东"},{"image":"https://pic4.zhimg.com/v2-1773af9d13783d8ed8f8f8c7a59a10b3.jpg","type":0,"id":9701854,"ga_prefix":"112609","title":"一幅画就是一个，不，好多个故事"},{"image":"https://pic3.zhimg.com/v2-b6c17691a29f122bcc809dccfefff77e.jpg","type":0,"id":9702319,"ga_prefix":"112519","title":"在公共场所吃螺蛳粉致他人死亡，算犯罪吗？"},{"image":"https://pic1.zhimg.com/v2-c2f41f47c980721857c7e29795789464.jpg","type":0,"id":9702170,"ga_prefix":"112517","title":"环形桥梁、隐形阁楼\u2026\u2026妙哉，妙哉"}]
     */

    private var date: String? = null
    private var stories: List<StoriesBean>? = null
    private var top_stories: List<TopStoriesBean>? = null

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun getStories(): List<StoriesBean>? {
        return stories
    }

    fun setStories(stories: List<StoriesBean>) {
        this.stories = stories
    }

    fun getTop_stories(): List<TopStoriesBean>? {
        return top_stories
    }

    fun setTop_stories(top_stories: List<TopStoriesBean>) {
        this.top_stories = top_stories
    }

    class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-fa459d5f13d04ffecbaad27b9db633f5.jpg"]
         * type : 0
         * id : 9702404
         * ga_prefix : 112615
         * title : 一个坏消息：世界首例基因编辑婴儿在中国诞生
         * multipic : true
         */

        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
        var isMultipic: Boolean = false
        var images: List<String>? = null
    }

    class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-05c2d2850902a8f03f1835f787c0c55d.jpg
         * type : 0
         * id : 9702404
         * ga_prefix : 112615
         * title : 一个坏消息：世界首例基因编辑婴儿在中国诞生
         */

        var image: String? = null
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
    }

}