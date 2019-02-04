package com.example.a.zhihu.Details

import com.example.a.zhihu.Data.DetailData

interface WebContract {
    interface UIView{
        fun onNull()
        fun onError()
        fun updata(data : DetailData)
        fun getID() : Int
    }
    interface Presenter{
        fun addData()
    }
}