package com.example.homework.childActivity;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.articleAdapter;
import com.example.homework.R;
import com.example.homework.bean.articleBean;
import com.google.gson.Gson;

public class article extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private articleAdapter mArticleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mRecyclerView = findViewById(R.id.rv_article);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",2);
        netLoadArticle(id);
        mArticleAdapter=new articleAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mArticleAdapter);
    }

    private void netLoadArticle(int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    String url = "https://www.wanandroid.com/article/list/" + i + "/json?cid=" + id;
                    String result = doGet(url);
                    Gson gson = new Gson();
                    articleBean article = gson.fromJson(result, articleBean.class);
                    updateUIArticle(article);
                }
            }
        }).start();
    }

    private void updateUIArticle(articleBean article) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            mArticleAdapter.setDate(article);
            }
        });
    }
}