package com.example.midtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.midtest.Repository
import com.example.midtest.model.Message

class MessageViewModel :ViewModel() {
    //对外部设置为不可见,防止其改变livedata里的数据
    private val searchLiveData=MutableLiveData<String>()
    val messageList=ArrayList<Message.Comment>()
    val messageLiveData=searchLiveData.switchMap { query->
        Repository.getMessage(query)
    }
    fun message(query:String){
        searchLiveData.value=query
    }
}