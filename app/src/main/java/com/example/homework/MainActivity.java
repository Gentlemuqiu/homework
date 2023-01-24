package com.example.homework;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.homework.Adapter.FragmentAdapter;
import com.example.homework.Fragment.home;
import com.example.homework.Fragment.love;
import com.example.homework.Fragment.mine;
import com.example.homework.Fragment.playGround;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragmentList;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }


    private void initData() {
        mFragmentList = new ArrayList<>();
        //将四个底部导航页装入集合

        home home = com.example.homework.Fragment.home.newInstance("首页", " ");
        playGround playGround = com.example.homework.Fragment.playGround.newInstance("广场", " ");
        love love = com.example.homework.Fragment.love.newInstance("收藏", " ");
        mine mine = com.example.homework.Fragment.mine.newInstance("我的", " ");
        mFragmentList.add(home);
        mFragmentList.add(playGround);
        mFragmentList.add(love);
        mFragmentList.add(mine);
    }

    private void initView() {
        mViewpager = findViewById(R.id.vp);
        mBottomNavigationView = findViewById(R.id.bottom_menu);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
    }

    private void initEvent() {
        mViewpager.setAdapter(mFragmentAdapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //页面滑动时底部导航跟着动
                onPagerSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //返回false 选择item不会被选中,  返回true 选择item会被选中
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_playGround:
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_love:
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.menu_mine:
                        mViewpager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void onPagerSelected(int position) {
        switch (position) {
            case 0:
                mBottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
                mBottomNavigationView.setSelectedItemId(R.id.menu_playGround);
                break;
            case 2:
                mBottomNavigationView.setSelectedItemId(R.id.menu_love);
                break;
            case 3:
                mBottomNavigationView.setSelectedItemId(R.id.menu_mine);
                break;
            default:
                break;
        }
    }

}