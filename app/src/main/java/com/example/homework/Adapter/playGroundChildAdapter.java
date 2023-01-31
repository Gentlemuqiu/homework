package com.example.homework.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class playGroundChildAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitleName;
    public playGroundChildAdapter(@NonNull FragmentManager fm, List<Fragment> Fragments, List<String> TitleName) {
        super(fm);
        this.mFragments=Fragments;
        this.mTitleName=TitleName;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitleName.size();
    }
    //显示title
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleName.get(position);
    }
}
