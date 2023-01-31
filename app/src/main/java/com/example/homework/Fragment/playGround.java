package com.example.homework.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.homework.Adapter.playGroundChildAdapter;
import com.example.homework.R;
import com.example.homework.childFragment.Navigation;
import com.example.homework.childFragment.account;
import com.example.homework.childFragment.item;
import com.example.homework.childFragment.search;
import com.example.homework.childFragment.system;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class playGround extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragments;
    private List<String> mTitleName;
    private playGroundChildAdapter mPlayGroundChildAdapter;
    public playGround() {
    }

    public static playGround newInstance(String param1, String param2) {
        playGround fragment = new playGround();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_ground, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager=view.findViewById(R.id.vp_playground);
        mTabLayout=view.findViewById(R.id.tab_layout);
        initData();
        mPlayGroundChildAdapter=new playGroundChildAdapter(getActivity().getSupportFragmentManager(), mFragments,mTitleName);
        mViewPager.setAdapter(mPlayGroundChildAdapter);
        //使Fragment与TabLayout进行联动
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        //创建Fragment集合 在PlayGround中容纳多个Fragment
        mFragments=new ArrayList<>();
        system system= com.example.homework.childFragment.system.newInstance("体系","");
        Navigation navigation=com.example.homework.childFragment.Navigation.newInstance("导航","");
        item item=com.example.homework.childFragment.item.newInstance("项目","");
        search search=com.example.homework.childFragment.search.newInstance("发现","");
        account account=com.example.homework.childFragment.account.newInstance("导航","");
        mFragments.add(system);
        mFragments.add(navigation);
        mFragments.add(item);
        mFragments.add(search);
        mFragments.add(account);
        //为各个Fragment添加名字
        mTitleName=new ArrayList<>();
        mTitleName.add("体系");
        mTitleName.add("导航");
        mTitleName.add("项目");
        mTitleName.add("搜索");
        mTitleName.add("公众号");
    }
}