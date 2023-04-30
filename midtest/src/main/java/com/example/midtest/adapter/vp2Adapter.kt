package com.example.midtest.adapter

import android.content.Context
import android.util.Log
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
import kotlin.properties.Delegates

class vp2Adapter(private val context: Context, private val data: ArrayList<latest.TopStory>) :
    RecyclerView.Adapter<vp2Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.latest_image)
        val title: TextView = view.findViewById(R.id.title)
        val hint: TextView = view.findViewById(R.id.hint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vp2_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val realPosition = position % data.size
        Glide.with(context).load(data[realPosition].image).into(holder.image)
        holder.title.text = data[ realPosition].title
        holder.hint.text = data[realPosition].hint
    }


}