package com.example.midtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.midtest.Repository
import com.example.midtest.model.latest

class LatestViewModel : ViewModel() {
    val latestList = ArrayList<latest.TopStory>()
    val todayLiveData = Repository.latestNew()
    val latestList1 =ArrayList<latest.Story>()
}