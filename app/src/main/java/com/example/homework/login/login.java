package com.example.homework.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.homework.Adapter.loginAdapter;
import com.example.homework.R;

public class login extends AppCompatActivity {
    //设置启动页
    private int[] loginImageArray = {
            R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.action
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //引入ViewPager
        ViewPager mVpLogin = findViewById(R.id.vp_login);
        //添加适配器
        mVpLogin.setAdapter(new loginAdapter(login.this, loginImageArray));
    }
}