package com.example.homework.childActivity;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.teachChildAdapter;
import com.example.homework.R;
import com.example.homework.bean.teachChildBean;
import com.google.gson.Gson;

public class teachActivity extends AppCompatActivity {
 private RecyclerView mRecyclerView;
 private teachChildAdapter mTeachChildAdapter;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        initData();
        //网络加载
        NetLoad();
        mRecyclerView.setAdapter(mTeachChildAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void NetLoad() {
        String url="https://wanandroid.com/article/list/0/json?cid="+mId+"&order_type=1";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result=doGet(url);
                Gson gson=new Gson();
                teachChildBean teachChildBean=gson.fromJson(result, com.example.homework.bean.teachChildBean.class);
                //更新UI
                upDataUI(teachChildBean);
            }
        }).start();
    }

    private void upDataUI(teachChildBean teachChildBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTeachChildAdapter.setData(teachChildBean);
            }
        });
    }

    private void initData() {
        mRecyclerView=findViewById(R.id.rv_teachChild);
        mTeachChildAdapter=new teachChildAdapter();
        Intent intent=getIntent();
        mId = intent.getIntExtra("id",0);
    }

}