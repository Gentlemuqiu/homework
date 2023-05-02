package com.example.midtest.util
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.adapter.webAdapter
import com.example.midtest.databinding.ActivityWebBinding
import com.example.midtest.fragment.MainFragment


class WebActivity : AppCompatActivity() {
    private val mBinding by lazy {
        ActivityWebBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: webAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val data = intent.getStringArrayListExtra("story")
        val id = intent.getStringArrayListExtra("id")
        adapter = webAdapter(this, data!!)
        mBinding.vp2WebView.adapter = adapter
        mBinding.vp2WebView.setCurrentItem(MainFragment.myPosition,false)
        var response = 0
        //设置页面改变监听,然后获取对应文章的id
        mBinding.vp2WebView.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                response = position
                super.onPageSelected(position)
            }
        })
        mBinding.talk.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            intent.putExtra("response", id!![response])
            this.startActivity(intent)
        })
        //采用系统自带的分享
        mBinding.share.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"// 指定类型是纯文本分享
            intent.putExtra(Intent.EXTRA_SUBJECT,"标题")
            intent.putExtra(Intent.EXTRA_TEXT,data[response])
            startActivity(Intent.createChooser(intent,"分享"))
        })
    }
}