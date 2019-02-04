package com.example.a.zhihu

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.a.zhihu.Data.NewsData
import com.example.a.zhihu.Details.WebUI
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RecyclerAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val BANNER_TYPE = 0
    private val STORIES_TYPE = 1
    private var datePositon = 1
    private var lastDotPositon = 0
    private var stories: ArrayList<NewsData.StoriesBean> = ArrayList()
    private var positionAndDate: HashMap<Int, String> = HashMap()
    private var pagerAdapter = ViewPagerAdapter(context)
    private lateinit var linearLayout: LinearLayout
    var date: Date = Date()
    fun addRVData(stories: List<NewsData.StoriesBean>, date: String) {
        this.stories.addAll(stories)
        positionAndDate[datePositon] = date
        datePositon = this.stories.size + 1
        notifyDataSetChanged()

        Log.d("recyclerData", "addRVData")
    }

    fun addBannerData(topStories: List<NewsData.TopStoriesBean>) {
        pagerAdapter.addData(topStories)
        for (i in 1..topStories.size) {
            var view = View(context)
            view.setBackgroundResource(R.drawable.dot)
            var param = LinearLayout.LayoutParams(20, 20)
            view.isEnabled = i == 1
            if (i != 1) {
                param.leftMargin = 5
            }
            linearLayout.addView(view, param)
        }
        notifyDataSetChanged()

        Log.d("recyclerData", "addBannerData")

    }

    fun clearData() {
        stories.clear()
        positionAndDate.clear()
        pagerAdapter.clear()
        notifyDataSetChanged()
        lastDotPositon = 0
        datePositon = 1
        linearLayout.removeAllViews()
        Log.d("recyclerData", "ClearData")

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view: View
        return if (p1 == BANNER_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_banner, p0, false)
            linearLayout = view.findViewById(R.id.dotslayout)
            BannerHolder(view)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_item, p0, false)
            StoriesHolder(context, view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) BANNER_TYPE else STORIES_TYPE
    }

    override fun getItemCount(): Int {
        return stories.size + 1
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if (p0 is BannerHolder) {
            p0.viewpager.adapter = pagerAdapter
        } else if (p0 is StoriesHolder) {
            p0.storyTitle.text = stories[p1 - 1].title
            Log.d("recyclerData", stories[p1 - 1].title)
            Picasso.with(context).load(stories[p1 - 1].images?.get(0))
                .placeholder(R.drawable.timg)
                .error(R.drawable.error)
                .into(p0.storyImage)
            p0.id = stories[p1 - 1].id
            if (positionAndDate.containsKey(p1)) {
                if (p1 == 1) {
                    p0.dateView.text = "今日热闻"
                } else {
                    p0.dateView.text = getWeek(positionAndDate[p1].toString())
                }
                p0.dateView.visibility = View.VISIBLE
            } else {
                p0.dateView.visibility = View.GONE
            }
        }
    }

    inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewpager: ViewPager = itemView.findViewById(R.id.viewpager)

        init {
            viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(p0: Int) {
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                }

                override fun onPageSelected(p0: Int) {
                    linearLayout.getChildAt(p0).isEnabled = true
                    linearLayout.getChildAt(lastDotPositon).isEnabled = false
                    lastDotPositon = p0
                }

            })
        }

    }

    class StoriesHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        var storyTitle: TextView = itemView.findViewById(R.id.stories_title)
        var dateView: TextView = itemView.findViewById(R.id.date)
        var storyImage: ImageView = itemView.findViewById(R.id.stories_image)
        var id: Int? = null

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, WebUI::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
        }
    }

    fun formatDate(date: String): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("yymmdd")
        var d: Date? = null
        try {
            d = format.parse(date)
            Log.d("date", d.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        calendar.time = d!!
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        var str = ""
        when (day) {
            1 -> str = "星期日"
            2 -> str = "星期一"
            3 -> str = "星期二"
            4 -> str = "星期三"
            5 -> str = "星期四"
            6 -> str = "星期五"
            7 -> str = "星期六"
            else -> {
            }
        }
        return date.substring(4, 6) + "月" + date.substring(6) + "日 " + str
    }

    fun getWeek(date: String): String {
        val week = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        val d = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6)
        val s = d.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val c = Calendar.getInstance()
        c.set(
            Integer.parseInt(s[0]), Integer.parseInt(s[1]) - 1,
            Integer.parseInt(s[2])
        )

        if (date[4] != '0')
            return date.substring(4, 6) + "月" +
                    date.substring(6) + "日 " + week[c.get(Calendar.DAY_OF_WEEK) - 1]
        else {
            if (date[6] != '0')
                return date.substring(5,6) + "月" +
                        date.substring(6) + "日 " + week[c.get(Calendar.DAY_OF_WEEK) - 1]
            else {
                return date.substring(5,6) + "月" +
                        date.substring(7) + "日 " + week[c.get(Calendar.DAY_OF_WEEK) - 1]
            }
        }
    }
}