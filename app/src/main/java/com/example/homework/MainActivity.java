package com.example.homework;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
        //增加更多设置的菜单
        initMenu();
    }

    private void initMenu() {
        //设置顶部名称
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Gentle");
    }

    //绑定菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //被点击后触发相应的结果
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more:
                //跳到新创造的页面
                Intent intent = new Intent(this, SecondaryActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_drop:
                //加载网络
                NetLoad();
                return true;
            case R.id.menu_light:
                //设置日间模式
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            case R.id.menu_dark:
                //设置夜间模式
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void NetLoad() {
        String url = "https://www.wanandroid.com/user/logout/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doGet(url);
                //跳回主线程,并关闭界面
                updateUI();
            }
        }).start();
    }

    private void updateUI() {
        finish();
    }


    private void initData() {
        mFragmentList = new ArrayList<>();
        //将四个底部导航页装入集合
        home home = com.example.homework.Fragment.home.newInstance("首页", " ");
        playGround playGround = com.example.homework.Fragment.playGround.newInstance("广场", " ");
        love love = com.example.homework.Fragment.love.newInstance("收藏", " ");
        mine mine1 = com.example.homework.Fragment.mine.newInstance("我的", "");
        mFragmentList.add(home);
        mFragmentList.add(playGround);
        mFragmentList.add(love);
        mFragmentList.add(mine1);
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
        //当选择某个下标时跳到相应页面
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

    //当选中某一个页面时  下标也被选择
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