package com.example.homework.childActivity;

import static android.widget.Toast.makeText;
import static com.example.homework.Util.NetWorkPost.sendPost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.R;

public class addLove extends AppCompatActivity {
    private EditText et_author, et_title, et_link;
    private String mAuthor;
    private String mLink;
    private String mTitle;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlove);
        initData();
    }

    private void initData() {
        et_author = findViewById(R.id.et_author);
        et_link = findViewById(R.id.et_link);
        et_title = findViewById(R.id.et_title);
        mAuthor = et_author.getText().toString();
        mLink = et_link.getText().toString();
        mTitle = et_title.getText().toString();
    }

    public void add(View view) {
        if (mTitle == null) {
            makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mAuthor == null) {
            makeText(this, "作者不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mLink == null) {
            makeText(this, "链接不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        netLoad(mTitle, mAuthor, mLink);
    }

    private void netLoad(String title, String author, String link) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://www.wanandroid.com/lg/collect/add/json?title=" + title + "&author=" + author + "&link=" + link;
                String result=sendPost(url);
                Log.d("hui", "run: "+result);
                updateUI();
            }
        }).start();
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
             Toast.makeText(getBaseContext(),"添加成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}