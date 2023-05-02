package com.example.midtest.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtest.R
import java.time.format.DateTimeFormatter

class MessageAdapter(
    private val context: Context,
    private val data: ArrayList<com.example.midtest.model.Message.Comment>
) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.head)
        val userName: TextView = view.findViewById(R.id.name)
        val chinese: TextView = view.findViewById(R.id.chinese)
        val nowTime: TextView = view.findViewById(R.id.time)
        val likes: TextView = view.findViewById(R.id.likes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(data[position].avatar).into(holder.imageView)
        holder.chinese.text=data[position].content
        holder.userName.text=data[position].author
        holder.nowTime.text=data[position].time.toString().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        holder.likes.text=data[position].likes.toString()
    }

    override fun getItemCount(): Int = data.size
}