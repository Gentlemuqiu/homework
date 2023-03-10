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
        //???Fragment???TabLayout????????????
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        //??????Fragment?????? ???PlayGround???????????????Fragment
        mFragments=new ArrayList<>();
        system system= com.example.homework.childFragment.system.newInstance("??????","");
        Navigation navigation=com.example.homework.childFragment.Navigation.newInstance("??????","");
        item item=com.example.homework.childFragment.item.newInstance("??????","");
        search search=com.example.homework.childFragment.search.newInstance("??????","");
        account account=com.example.homework.childFragment.account.newInstance("??????","");
        mFragments.add(system);
        mFragments.add(navigation);
        mFragments.add(item);
        mFragments.add(search);
        mFragments.add(account);
        //?????????Fragment????????????
        mTitleName=new ArrayList<>();
        mTitleName.add("??????");
        mTitleName.add("??????");
        mTitleName.add("??????");
        mTitleName.add("??????");
        mTitleName.add("?????????");
    }
}