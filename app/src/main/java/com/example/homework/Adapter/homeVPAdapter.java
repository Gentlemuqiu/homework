package com.example.homework.Adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.homework.Util.webView;
import com.example.homework.bean.banner;

import java.util.ArrayList;
import java.util.List;

public class homeVPAdapter extends PagerAdapter {
    private final List<banner.DataDTO> mBannerList = new ArrayList<>();

    //顶部轮播
    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView bannerImageView = new ImageView(container.getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bannerImageView.setLayoutParams(lp);
        bannerImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(container.getContext()).load(mBannerList.get(position).getImagePath()).into(bannerImageView);
        bannerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), webView.class);
                intent.putExtra("url",mBannerList.get(position).getUrl());
                view.getContext().startActivity(intent);
            }
        });
        container.addView(bannerImageView);
        return bannerImageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setDate(banner banner) {
        mBannerList.clear();
        mBannerList.addAll(banner.getData());
        notifyDataSetChanged();
    }


}
