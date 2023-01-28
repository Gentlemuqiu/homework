package com.example.homework.childActivity;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.accountAdapter;
import com.example.homework.R;
import com.example.homework.bean.accountBean;
import com.google.gson.Gson;

public class Account extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private accountAdapter mAccountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mRecyclerView = findViewById(R.id.rv_account);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 2);
        netLoadArticle(id);
        mAccountAdapter = new accountAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAccountAdapter);
    }

    private void netLoadArticle(int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    String url = "https://wanandroid.com/wxarticle/list/" + id + "/" + i + "/json";
                    String result = doGet(url);
                    Gson gson = new Gson();
                    accountBean account = gson.fromJson(result, accountBean.class);
                    updateUIArticle(account);
                }
            }
        }).start();

    }

    private void updateUIArticle(accountBean account) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAccountAdapter.setDate(account);
            }
        });
    }
}