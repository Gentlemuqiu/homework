package com.example.midtest.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtest.adapter.MessageAdapter
import com.example.midtest.databinding.ActivityMessageBinding
import com.example.midtest.viewModel.MessageViewModel

class MessageActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this)[MessageViewModel::class.java] }

    private val mBinding by lazy {
        ActivityMessageBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val response = intent.getStringExtra("response")
        viewModel.message(response.toString())
        adapter= MessageAdapter(this,viewModel.messageList)
        mBinding.rvMessage.layoutManager = LinearLayoutManager(this)
        viewModel.messageLiveData.observe(this, Observer { result ->
            val comment = result.getOrNull()
            if (comment != null) {
                viewModel.messageList.clear()
                viewModel.messageList.addAll(comment.comments)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        mBinding.rvMessage.adapter = adapter
    }
}