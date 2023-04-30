package com.example.midtest.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtest.adapter.vp2Adapter
import com.example.midtest.databinding.FragmentMainBinding
import com.example.midtest.viewModel.LatestViewModel

/*
class MainFragment : Fragment() {
    private val latestViewModel by lazy { ViewModelProvider(this).get(LatestViewModel::class.java) }
    private val mBinding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private lateinit var adapter : mainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvMain.layoutManager= LinearLayoutManager(activity)
        latestViewModel.todayLiveData.observe(viewLifecycleOwner, Observer { result ->
            val data = result.getOrNull()
            if (data != null) {
                latestViewModel.latestList.clear()
                latestViewModel.latestList.addAll(data.topStories)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查阅到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        mBinding.rvMain.adapter =adapter
    }
}*/
