package com.example.a.zhihu.Details

import com.example.a.zhihu.Data.DetailData
import retrofit2.Call
import retrofit2.Response

class WebPresenter(var UIView : WebContract.UIView) : WebContract.Presenter {
    var model = WebModel()
    override fun addData() {
        var call : Call<DetailData> = model.getDetailData(UIView.getID())
        call.enqueue(object : retrofit2.Callback<DetailData> {
            override fun onFailure(call: Call<DetailData>, t: Throwable) {
                UIView.onError()
            }

            override fun onResponse(call: Call<DetailData>, response: Response<DetailData>) {
                var data = response.body()
                if(data == null){
                    UIView.onNull()
                }else{
                    UIView.updata(data)
                }
            }

        })
    }
}