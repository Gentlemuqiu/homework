package com.example.homework.childActivity;

import static com.example.homework.Util.NetWorkGet.doCookieGet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.commentAdapter;
import com.example.homework.R;
import com.example.homework.bean.commentBean;
import com.google.gson.Gson;

public class comment extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private commentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mRecyclerView = findViewById(R.id.rv_comment);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        //加载网络
        netLoad(id);
        mCommentAdapter = new commentAdapter();
        mRecyclerView.setAdapter(mCommentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void netLoad(int id) {
        String url = "https://wanandroid.com/wenda/comments/" + id + "/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doCookieGet(url);
                Gson gson = new Gson();
                commentBean commentBean = gson.fromJson(result, com.example.homework.bean.commentBean.class);
                //更新UI
                upDataUI(commentBean);
            }
        }).start();
    }

    private void upDataUI(commentBean commentBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //设置得到的数据
                mCommentAdapter.setData(commentBean);
            }
        });
    }
}