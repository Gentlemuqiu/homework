package com.example.midtest.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midtest.adapter.BeforeAdapter
import com.example.midtest.databinding.FragmentAgainBinding
import com.example.midtest.util.Time
import com.example.midtest.viewModel.BeforeViewModel


class AgainFragment : Fragment() {
    private val mBinding by lazy {
        FragmentAgainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy { ViewModelProvider(this)[BeforeViewModel::class.java] }
    private lateinit var adapter: BeforeAdapter
    private var day = 0
    private var day2=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataAfter()
        adapter = BeforeAdapter(this, viewModel.beforeList)
        mBinding.recyclerView.adapter = adapter
        loadBefore()
        loadAfter()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadBefore() {
//         下拉请求之前的数据，然后将刷新状态关闭
             mBinding.swipeRefresh.setOnRefreshListener {
            getDataBefore()
            mBinding.swipeRefresh.isRefreshing = false
        }
    }

    private fun loadAfter() {
//        这里设置滑动事件监听，用来判断是否滑动到最后一页
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
                    val totalItemCount = manager.itemCount
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
                isSlidingToLast = dy > 0
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    private fun getDataAfter() {
        viewModel.beforeNew(Time.getYesterday(day.toLong()))
        day2=day
        day++
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.beforeLiveData.observe(viewLifecycleOwner) { result ->
            val story = result.getOrNull()
            if (story != null) {
                viewModel.beforeList.clear()
                viewModel.beforeList.addAll(story.stories)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    private fun getDataBefore() {
        day2--
        day=day2+1
        if (day2 < 0) {
            Toast.makeText(activity, "已到达最顶端了", Toast.LENGTH_SHORT).show()
            day=0
            day2=0
            return
        }
        viewModel.beforeNew(Time.getYesterday(day2.toLong()))
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.beforeLiveData.observe(viewLifecycleOwner) { result ->
            val story = result.getOrNull()
            if (story != null) {
                viewModel.beforeList.clear()
                viewModel.beforeList.addAll(story.stories)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }
}
