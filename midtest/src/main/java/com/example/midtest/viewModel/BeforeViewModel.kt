package com.example.midtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.midtest.Repository
import com.example.midtest.model.before

class BeforeViewModel : ViewModel() {
    //对外部设置为不可见,防止其改变livedata里的数据
    private val searchLiveData = MutableLiveData<String>()
    val beforeList = ArrayList<before.Story>()
    val beforeLiveData = searchLiveData.switchMap { query ->
        Repository.beforeNew(query)
    }

    fun beforeNew(query: String) {
        searchLiveData.value = query
    }
}