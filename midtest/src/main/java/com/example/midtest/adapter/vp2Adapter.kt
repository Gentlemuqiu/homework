package com.example.midtest.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtest.R
import com.example.midtest.model.latest
import com.example.midtest.util.Time

class vp2Adapter(private val fragment: Fragment, private val data: ArrayList<latest.TopStory>) :
    RecyclerView.Adapter<vp2Adapter.ViewHolder>() {
   @RequiresApi(Build.VERSION_CODES.O)
   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.latest_image)
        val title: TextView = view.findViewById(R.id.title)
        val hint: TextView = view.findViewById(R.id.hint)
       val time :TextView=view.findViewById(R.id.time)
        init {
        time.text= Time.dataString
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.vp2_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val realPosition = position % data.size
        Glide.with(fragment).load(data[realPosition].image).into(holder.image)
        holder.title.text = data[ realPosition].title
        holder.hint.text = data[realPosition].hint
    }


}