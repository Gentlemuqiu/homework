package com.example.midtest.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtest.R
import com.example.midtest.model.before
import com.example.midtest.model.latest

class beforeAdapter(private val fragment: Fragment, private val data: ArrayList<before.Story>) :
    RecyclerView.Adapter<beforeAdapter.ViewHolder>() {
    inner  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.nowImage)
        val title: TextView = view.findViewById(R.id.nowTitle)
        val hint: TextView = view.findViewById(R.id.nowHint)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.now_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val realPosition = position % data.size
        Glide.with(fragment).load(data[realPosition].images[0]).into(holder.image)
        holder.title.text = data[realPosition].title
        holder.hint.text = data[realPosition].hint
    }

    override fun getItemCount(): Int = data.size
}