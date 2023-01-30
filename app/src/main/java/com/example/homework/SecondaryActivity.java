package com.example.homework;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.homework.Fragment.answer;
import com.example.homework.Fragment.teach;
import com.example.homework.Fragment.tool;
import com.google.android.material.navigation.NavigationView;

public class SecondaryActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        initData();
        initMenu();
        initEvent();
    }

    private void initEvent() {
        //设置每个栏目被点击后的事件监听
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_answer:
                        answer answer = com.example.homework.Fragment.answer.newInstance("首页", "");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fcv_left, answer)
                                .commit();
                        break;
                    case R.id.menu_tool:
                        tool tool = com.example.homework.Fragment.tool.newInstance("s", "");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fcv_left, tool)
                                .commit();
                        break;
                    case R.id.menu_teach:
                        teach teach = com.example.homework.Fragment.teach.newInstance("1", "");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fcv_left, teach)
                                .commit();
                        break;
                    case R.id.menu_back:
                        finish();
                        return true;
                }
                //设置左边菜单栏点击后关闭
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void initData() {
        //设置自己的toolbar
        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = findViewById(R.id.dl_left);
        mNavigationView = findViewById(R.id.nv_menu);
        //设置首页的fragment
        answer answer = com.example.homework.Fragment.answer.newInstance("首页", "");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fcv_left, answer)
                .commit();
        mNavigationView.setCheckedItem(R.id.menu_answer);
        //将toolbar和draw关联起来
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                0,
                0);
        //可以监听到打开和关闭
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        //同步一下
        mActionBarDrawerToggle.syncState();
    }

    private void initMenu() {
        //设置顶部名称
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("暮秋");
    }
}