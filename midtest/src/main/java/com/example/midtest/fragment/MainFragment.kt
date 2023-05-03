package com.example.midtest.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtest.adapter.MainAdapter
import com.example.midtest.databinding.FragmentMainBinding
import com.example.midtest.viewModel.LatestViewModel


class MainFragment : Fragment() {
    private val latestViewModel by lazy { ViewModelProvider(this)[LatestViewModel::class.java] }
    private val mBinding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //加载布局
        initView()
        //执行逻辑处理
        doLogic()
        //执行刷新操作
        doRefresh()
    }

    private fun initView() {
        mBinding.rvMain.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(this, latestViewModel.latestList, latestViewModel.latestList1)
    }

    private fun doRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener {
            //刷新时,再次请求一次数据
            doLogic()
            //将刷新状态取消
            mBinding.swipeRefresh.isRefreshing = false
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun doLogic() {
        //对数据进行观察
        latestViewModel.todayLiveData.observe(viewLifecycleOwner) { result ->
            val data = result.getOrNull()
            if (data != null) {
                latestViewModel.latestList.clear()
                latestViewModel.latestList.addAll(data.topStories)
                latestViewModel.latestList1.clear()
                latestViewModel.latestList1.addAll(data.stories)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
        mBinding.rvMain.adapter = adapter
    }
}
