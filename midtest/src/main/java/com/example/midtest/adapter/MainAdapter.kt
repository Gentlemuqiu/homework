package com.example.midtest.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.R
import com.example.midtest.fragment.MainFragment
import com.example.midtest.model.latest

class MainAdapter(
    private val fragment: Fragment,
    private val data: ArrayList<latest.TopStory>,
    private val data1: ArrayList<latest.Story>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var mLooperTask: Runnable
    private lateinit var handler: MyHandler
    //设置第一次请求的位置
    var index = 0

    //定义一个MyHandler
    @SuppressLint("HandlerLeak")
    inner class MyHandler : Handler(Looper.myLooper()!!)

    @SuppressLint("ClickableViewAccessibility")
    inner class ImageViewHolder(view: View, data: ArrayList<latest.TopStory>, fragment: Fragment) :
        RecyclerView.ViewHolder(view) {
        val vp2Adapter = Vp2Adapter(fragment, data)
        val vp2 = view.findViewById<ViewPager2>(R.id.vp2)
        val manyPoint = view.findViewById<LinearLayout>(R.id.manyPoint)
        //在init中进行这些操作,可以节省资源
        init {
            //为viewPager2设置页面改变监听
            vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                //页面选择监听,当到达对应位置时设置变亮
                override fun onPageSelected(position: Int) {
                    for (i in 0 until manyPoint.childCount) {
                        val point = manyPoint[i]
                        if (i != position % data.size) {
                            point.setBackgroundResource(R.drawable.shape_point_normal)
                        } else {
                            point.setBackgroundResource(R.drawable.shape_point_selected)
                        }
                        MainFragment.myPosition = position
                    }
                }
            })
        }
    }
    class NowViewHolder(view: View, fragment: Fragment, data1: ArrayList<latest.Story>) :
        RecyclerView.ViewHolder(view) {
        val nowAdapter = NowAdapter(fragment, data1)
        val nowRecyclerView = view.findViewById<RecyclerView>(R.id.now_recycler)
        init {
            nowRecyclerView.layoutManager = LinearLayoutManager(fragment.context)
        }
    }
    //根据不同条目显示不同类型
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            handler = MyHandler()
            ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vp2, parent, false),
                data,
                fragment
            )
        } else {
            NowViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.now_recycler, parent, false),
                fragment,
                data1
            )
        }
    }

    override fun getItemCount(): Int = 2

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.vp2.adapter = holder.vp2Adapter
            //有了图片后再插入点
            insertPoint(holder)
            //做循环
            doCycle(holder)
            //手动设置第一次循环
            handler.postDelayed(mLooperTask, 3000)
        } else if (holder is NowViewHolder) {
            holder.nowRecyclerView.adapter = holder.nowAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun doCycle(holder: ImageViewHolder) {
        //套娃循环
        mLooperTask = object : Runnable {
            override fun run() {
                //切换viewPager2里的图片到下一个
                if (index % data.size == 0)
                    holder.vp2.setCurrentItem(index % data.size, false)
                else holder.vp2.setCurrentItem(index % data.size, true)
                //第一次时Runnable还没构建完成所以需要手动handle下
                handler.postDelayed(this, 3000)
                index++
            }
        }
    }
   //插入点
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun insertPoint(holder: ImageViewHolder) {
        for (i in 0 until data.size) {
            val point = View(fragment.context)
            //设置宽高
            val layoutParams = LinearLayout.LayoutParams(15, 15)
            //设置背景
            point.background = fragment.context?.getDrawable(R.drawable.shape_point_normal)
            //设置点之间的间距
            layoutParams.leftMargin = 20
            //赋值
            point.layoutParams = layoutParams
            //在LinerLayout中添加点。
            holder.manyPoint.addView(point)
        }
    }


}
