package com.example.midtest.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtest.R
import com.example.midtest.model.before
import com.example.midtest.util.WebActivity

class BeforeAdapter(private val fragment: Fragment, private val data: ArrayList<before.Story>) :
    RecyclerView.Adapter<BeforeAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.nowImage)
        val title: TextView = view.findViewById(R.id.nowTitle)
        val hint: TextView = view.findViewById(R.id.nowHint)
        val skip: LinearLayout = view.findViewById(R.id.Skip)

        init {
            skip.setOnClickListener {
                val intent = Intent(fragment.context, WebActivity::class.java)
                val list = ArrayList<String>()
                val list1 = ArrayList<String>()
                for (i in 0 until data.size) {
                    list.add(data[i].url)
                    list1.add(data[i].id)
                }

                intent.putExtra("story", list)
                intent.putExtra("id", list1)
                view.context.startActivity(intent)
            }
        }
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