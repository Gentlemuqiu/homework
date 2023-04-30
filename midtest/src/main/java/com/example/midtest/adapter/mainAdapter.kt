package com.example.midtest.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.R
import com.example.midtest.model.latest

class mainAdapter(private val fragment: Fragment, private val data: ArrayList<latest.TopStory>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ImageViewHolder(view: View,data: ArrayList<latest.TopStory>,fragment: Fragment) : RecyclerView.ViewHolder(view) {
        val vp2Adapter = vp2Adapter(fragment, data)
        val vp2 =view.findViewById<ViewPager2>(R.id.vp2)
    }

    class nowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun getItemId(position: Int): Long {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vp2, parent, false)
            ,data,fragment)
        } else {
            nowViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.now_recycler, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.vp2.adapter=holder.vp2Adapter
        } else if(holder is nowViewHolder) {

        }
    }
}