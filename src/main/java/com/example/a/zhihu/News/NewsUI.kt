package com.example.a.zhihu.News

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.a.zhihu.Data.NewsData
import com.example.a.zhihu.R
import com.example.a.zhihu.RecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsUI : AppCompatActivity(), NewsContract.UIView {

    private lateinit var recycler: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var refresh: SwipeRefreshLayout
    private var presenter = NewsPresenter(this)
    private var key = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        recycler = findViewById(R.id.recyclerview)
        initRecyclerAdapter()
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                presenter.addData()
            }
        }
        initRefresh()
        initScroll()
    }

    fun initRecyclerAdapter() {
        recyclerAdapter = RecyclerAdapter(this)
        val recyclerLayoutManager = LinearLayoutManager(this)
        recyclerLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = recyclerLayoutManager
        recycler.adapter = recyclerAdapter
    }

    fun initRefresh() {
        refresh = findViewById(R.id.refresh)
        refresh.setOnRefreshListener {
            refreshData()
            refresh.isRefreshing = false
        }
    }

    fun initScroll() {
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var totalItemCount: Int = 0
            private var firstVisibleItem: Int = 0
            private var visibleItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = (recyclerView.layoutManager as LinearLayoutManager?)!!
                totalItemCount = manager.itemCount
                firstVisibleItem = manager.findFirstVisibleItemPosition()
                visibleItemCount = recyclerView.childCount
                if (totalItemCount - visibleItemCount <= firstVisibleItem && key) {
                    key = false
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            presenter.addData()
                        }
                    }
                }
            }
        })
    }

    override fun onError() {
        key = true
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show()
    }

    override fun onNull() {
        key = true
        Toast.makeText(this, "没有您想要的内容", Toast.LENGTH_SHORT).show()
    }

    override fun addRecyclerData(stories: List<NewsData.StoriesBean>, date: String) {
        recyclerAdapter.addRVData(stories, date)
        key = true
        Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show()
    }

    override fun addBannerData(topStories: List<NewsData.TopStoriesBean>) {
        recyclerAdapter.addBannerData(topStories)
    }

    override fun refreshData() {
        key = false
        recyclerAdapter.clearData()
        presenter.clearData()
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                presenter.addData()
            }
        }
    }
}
