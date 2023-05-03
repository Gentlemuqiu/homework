package com.example.midtest.util
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.midtest.adapter.WebAdapter
import com.example.midtest.databinding.ActivityWebBinding
import com.example.midtest.fragment.MainFragment


class WebActivity : AppCompatActivity() {
    private val mBinding by lazy {
        ActivityWebBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: WebAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val data = intent.getStringArrayListExtra("story")
        val id = intent.getStringArrayListExtra("id")
        adapter = WebAdapter(this, data!!)
        mBinding.vp2WebView.adapter = adapter
   //   设置这个的目的是为了传递给评论，让他知道请求的是哪个文章的评论
        var response = 0
        //设置页面改变监听,然后获取对应文章的id
        mBinding.vp2WebView.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                response = position
                super.onPageSelected(position)
            }
        })
        mBinding.talk.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            intent.putExtra("response", id!![response])
            this.startActivity(intent)
        }
        //采用系统自带的分享
        mBinding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"// 指定类型是纯文本分享
            intent.putExtra(Intent.EXTRA_SUBJECT, "标题")
            intent.putExtra(Intent.EXTRA_TEXT, data[response])
            startActivity(Intent.createChooser(intent, "分享"))
        }
    }
}