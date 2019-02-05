package com.example.a.zhihu

import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.a.zhihu.Data.NewsData
import com.example.a.zhihu.Data.TopStory
import com.example.a.zhihu.Details.WebUI
import com.squareup.picasso.Picasso

class ViewPagerAdapter(
    private var context: Context,
    private var topStories: List<TopStory>
) : PagerAdapter() {


//    fun addData(topStories: List<NewsData.TopStoriesBean>) {
//        this.topStories.addAll(topStories)
//    }
//
//    fun clear() {
//        topStories.clear()
//    }
//
//    fun dataIsEmpty(): Boolean {
//        return topStories.isEmpty()
//    }

    fun getData(): String {
        return topStories.toString()
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return topStories.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.banner_item, container, false)
        val imageView: ImageView = view.findViewById(R.id.banner_image)
        val textView: TextView = view.findViewById(R.id.banner_title)
        Picasso.with(context).load(topStories[position].image)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.timg)
            .error(R.drawable.error)
            .into(imageView)
        textView.text = topStories[position].title
        view.setOnClickListener {
            val intent = Intent(context, WebUI::class.java)
            intent.putExtra("id", topStories[position].id)
            context.startActivity(intent)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}