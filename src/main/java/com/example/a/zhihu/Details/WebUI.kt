package com.example.a.zhihu.Details

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import com.example.a.zhihu.Data.DetailData
import com.example.a.zhihu.R
import kotlinx.android.synthetic.main.web.*

class WebUI : AppCompatActivity() , WebContract.UIView {
    var id : Int? = null
    var presenter = WebPresenter(this)
    lateinit var webView: WebView
    override fun getID(): Int {
        return id as Int
    }

    override fun onNull() {
        Toast.makeText(this,"没有您想要的内容", Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show()
    }

    override fun updata(data : DetailData) {
        val body : String = data.body.replace("<img", "<img style='max-width:100%;height:auto;'")
        webView.loadDataWithBaseURL(null, "<html><body>$body</body></html", "text/html", "utf-8", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web)

        val intent = intent
        id = intent.getIntExtra("id",0)

        webView = findViewById(R.id.webview)
        webView.settings.apply{
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            setSupportZoom(true)
        }

        presenter.addData()
    }
}