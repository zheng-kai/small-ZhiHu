package com.example.a.zhihu.Data

data class DetailData(
    var body: String,
    var css: List<String>,
    var ga_prefix: String,
    var id: Int,
    var image: String,
    var image_source: String,
    var images: List<String>,
    var js: List<Any>,
    var share_url: String,
    var title: String,
    var type: Int
)