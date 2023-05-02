package com.example.midtest.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.postDelayed
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.MainActivity
import com.example.midtest.R
import com.example.midtest.fragment.MainFragment
import com.example.midtest.model.latest

class mainAdapter(
    private val fragment: Fragment,
    private val data: ArrayList<latest.TopStory>,
    private val data1: ArrayList<latest.Story>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    lateinit var mLooperTask: Runnable
    lateinit var handler: Handler
    var index = 0

    @SuppressLint("ClickableViewAccessibility")
    inner class ImageViewHolder(view: View, data: ArrayList<latest.TopStory>, fragment: Fragment) :
        RecyclerView.ViewHolder(view) {
        val vp2Adapter = vp2Adapter(fragment, data)
        val vp2 = view.findViewById<ViewPager2>(R.id.vp2)
        val manyPoint = view.findViewById<LinearLayout>(R.id.manyPoint)
        //在init中进行这些操作,可以节省资源
        init {
            vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    for (i in 0 until manyPoint.childCount) {
                        val point = manyPoint[i]
                        if (i != position % data.size) {
                            point.setBackgroundResource(R.drawable.shape_point_normal)
                        } else {
                            point.setBackgroundResource(R.drawable.shape_point_selected)
                        }
                        MainFragment.myPosition=position
                    }
                }
            })
        }


    }

    class nowViewHolder(view: View, fragment: Fragment, data1: ArrayList<latest.Story>) :
        RecyclerView.ViewHolder(view) {
        val nowAdapter = nowAdapter(fragment, data1)
        val nowRecyclerView = view.findViewById<RecyclerView>(R.id.now_recycler)
        init {
            nowRecyclerView.layoutManager = LinearLayoutManager(fragment.context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == 0) {
            handler = Handler()
            ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vp2, parent, false),
                data,
                fragment
            )
        } else {
            nowViewHolder(
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
            doCycle(holder)
            handler.postDelayed(mLooperTask,3000)
        } else if (holder is nowViewHolder) {
            holder.nowRecyclerView.adapter = holder.nowAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun doCycle(holder: ImageViewHolder) {
        //套娃循环
         mLooperTask = object : Runnable {
            override fun run() {
                //切换viewPager2里的图片到下一个
                if (index % data.size == 0 )
                    holder.vp2.setCurrentItem(index % data.size, false)
                else holder.vp2.setCurrentItem(index % data.size, true)
                //第一次时Runnable还没构建完成所以需要手动handle下
                handler.postDelayed(this, 3000)
                index++
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun insertPoint(holder: ImageViewHolder) {
        for (i in 0 until data.size) {
            val point = View(fragment.context)
            val layoutParams = LinearLayout.LayoutParams(15, 15)
            point.background = fragment.context?.getDrawable(R.drawable.shape_point_normal)
            layoutParams.leftMargin = 20
            point.layoutParams = layoutParams
            holder.manyPoint.addView(point)
        }
    }


}
