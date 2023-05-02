package com.example.midtest.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midtest.adapter.beforeAdapter
import com.example.midtest.databinding.FragmentAgainBinding
import com.example.midtest.util.Time
import com.example.midtest.viewModel.BeforeViewModel
import kotlin.math.log


class againFragment : Fragment() {
    private val mBinding by lazy {
        FragmentAgainBinding.inflate(layoutInflater)
    }
    val viewModel by lazy { ViewModelProvider(this).get(BeforeViewModel::class.java) }
    private lateinit var adapter: beforeAdapter
    var day = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataAfter()
        adapter= beforeAdapter(this,viewModel.beforeList)
        mBinding.recyclerView.adapter=adapter
        loadBefore()
        loadAfter()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadBefore() {
         mBinding.swipeRefresh.setOnRefreshListener {
             getDataBefore()
             mBinding.swipeRefresh.isRefreshing=false
        }
    }

    private fun loadAfter() {
        mBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            var isSlidingToLast = false
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //设置什么布局管理器,就获取什么的布局管理器
                val manager = recyclerView.layoutManager as LinearLayoutManager?
                // 当停止滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition ,角标值
                    val lastVisibleItem = manager!!.findLastCompletelyVisibleItemPosition()
                    //所有条目,数量值
                    val totalItemCount = manager!!.itemCount
                    // 判断是否滚动到底部，并且是向下滚动
                    if (lastVisibleItem == totalItemCount - 1 && isSlidingToLast) {
                        //加载更多功能的代码
                        getDataAfter()
                        Toast.makeText(activity, "上拉加载成功", Toast.LENGTH_SHORT).show()

                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                //dx>0:向右滑动,dx<0:向左滑动
                //dy>0:向下滑动,dy<0:向上滑动
                isSlidingToLast = if (dy > 0) {
                    true
                } else {
                    false
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    private fun getDataAfter() {
        viewModel.beforeNew(Time.getYesterday(day.toLong()))
        day++
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.beforeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val story=result.getOrNull()
            if (story!=null){
                viewModel.beforeList.clear()
                viewModel.beforeList.addAll(story.stories)
                adapter.notifyDataSetChanged()
            }else {
                Toast.makeText(activity, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    private fun getDataBefore() {
        --day
        Log.d("hui", "getDataBefore: ${day}")
        if(day<0){
            Toast.makeText(activity,"已到达最顶端了",Toast.LENGTH_SHORT).show()
            Log.d("hui", "getDataBefore: ${day}")
            day++
            return
        }
        viewModel.beforeNew(Time.getYesterday(day.toLong()))
        Log.d("hui", "getData: ${Time.getYesterday(day.toLong())} ")
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.beforeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val story=result.getOrNull()
            if (story!=null){
                viewModel.beforeList.clear()
                viewModel.beforeList.addAll(story.stories)
                adapter.notifyDataSetChanged()
            }else {
                Toast.makeText(activity, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}
