package com.example.midtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.MainActivity
import com.example.midtest.MainActivity.Companion.handler
import com.example.midtest.R
import com.example.midtest.model.latest

class mainAdapter(
    private val context: Context,
    private val data: ArrayList<latest.TopStory>,
    private val data1: ArrayList<latest.Story>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
         lateinit var mLooperTask: Runnable
    }


    class ImageViewHolder(view: View, data: ArrayList<latest.TopStory>, context: Context) :
        RecyclerView.ViewHolder(view) {
        val vp2Adapter = vp2Adapter(context, data)
        val vp2 = view.findViewById<ViewPager2>(R.id.vp2)
        val manyPoint = view.findViewById<LinearLayout>(R.id.manyPoint)
    }

    class nowViewHolder(view: View, context: Context, data1: ArrayList<latest.Story>) :
        RecyclerView.ViewHolder(view) {
        val nowAdapter = nowAdapter(context, data1)
        val nowRecyclerView = view.findViewById<RecyclerView>(R.id.now_recycler)
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
            ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vp2, parent, false),
                data,
                context
            )
        } else {
            nowViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.now_recycler, parent, false),
                context,
                data1
            )
        }
    }

    override fun getItemCount(): Int = 2

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.vp2.adapter = holder.vp2Adapter
            insertPoint(holder)
            doCycle(holder)

        } else if (holder is nowViewHolder) {
            holder.nowRecyclerView.adapter = holder.nowAdapter
            holder.nowRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun doCycle(holder: ImageViewHolder) {
        var index = 0
        mLooperTask = object : Runnable {
            override fun run() {
                //切换viewPager2里的图片到下一个
                if (index % data.size == 0)
                    holder.vp2.setCurrentItem(index % data.size, false)
                else
                    holder.vp2.setCurrentItem(index % data.size, true)
                ++index
                handler.postDelayed(mLooperTask, 3000)
            }
        }
        holder.vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until holder.manyPoint.childCount) {
                    val point = holder.manyPoint.get(i)
                    if (i != position % data.size) {
                        point.setBackgroundResource(R.drawable.shape_point_normal)
                    } else {
                        point.setBackgroundResource(R.drawable.shape_point_selected)
                    }
                }
            }
        })

        holder.vp2.getChildAt(0).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    MainActivity.handler.removeCallbacks(mLooperTask)
                }
                MotionEvent.ACTION_UP -> {
                    MainActivity.handler.postDelayed(mLooperTask, 3000)
                }
            }
            return@setOnTouchListener false
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun insertPoint(holder: ImageViewHolder) {
        for (i in 0 until data.size) {
            val point = View(this.context)
            val layoutParams = LinearLayout.LayoutParams(15, 15)
            point.background = context.getDrawable(R.drawable.shape_point_normal)
            layoutParams.leftMargin = 20
            point.layoutParams = layoutParams
            holder.manyPoint.addView(point)
        }
    }



}
