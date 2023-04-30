package com.example.midtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtest.R
import com.example.midtest.model.latest

class nowAdapter(private val fragment: Fragment, private val data: ArrayList<latest.Story>) :
    RecyclerView.Adapter<nowAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.nowImage)
        val title: TextView = view.findViewById(R.id.nowTitle)
        val hint: TextView = view.findViewById(R.id.nowHint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.now_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        Glide.with(fragment).load(data[position].images).into(holder.image)
        holder.title.text=data[position].title
        holder.hint.text=data[position].hint
    }

    override fun getItemCount(): Int = data.size
}