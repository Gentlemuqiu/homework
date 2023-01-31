package com.example.homework.Util;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homework.R;

public class webView extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.wv_webView);
        //拿到url
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        //加载数据
        mWebView.loadUrl(url);
    }
}