package com.example.midtest

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.adapter.mainAdapter
import com.example.midtest.adapter.mainAdapter.Companion.mLooperTask
import com.example.midtest.adapter.nowAdapter
import com.example.midtest.adapter.vp2Adapter
import com.example.midtest.databinding.ActivityMainBinding
import com.example.midtest.databinding.Vp2Binding
import com.example.midtest.model.latest
import com.example.midtest.viewModel.LatestViewModel

class MainActivity : AppCompatActivity() {
    private val latestViewModel by lazy { ViewModelProvider(this).get(LatestViewModel::class.java) }
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: mainAdapter

    companion object {
        lateinit var handler: Handler
    }

    private lateinit var view: View

    // private lateinit var mPointContainer :LinearLayout
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        doLogic()
        handler = Handler()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun doLogic() {
        mBinding.rvMain.layoutManager = LinearLayoutManager(this)
        adapter = mainAdapter(this, latestViewModel.latestList, latestViewModel.latestList1)
        latestViewModel.todayLiveData.observe(this, Observer { result ->
            val data = result.getOrNull()
            if (data != null) {
                latestViewModel.latestList.clear()
                latestViewModel.latestList.addAll(data.topStories)
                latestViewModel.latestList1.clear()
                latestViewModel.latestList1.addAll(data.stories)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "未能查阅到任何信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        mBinding.rvMain.adapter = adapter
    }



   /* override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        handler.post(mLooperTask)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        //取消循环
        handler.removeCallbacks(mLooperTask)
    }*/

}