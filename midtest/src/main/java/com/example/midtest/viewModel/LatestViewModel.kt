package com.example.midtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.midtest.Repository
import com.example.midtest.model.latest

class LatestViewModel : ViewModel() {
    //对外部设置为不可见,防止其改变livedata里的数据
    private val latestLiveData = MutableLiveData<String>()
    val latestList= ArrayList<latest.TopStory>()
    val todayLiveData = Repository.latestNew()


}