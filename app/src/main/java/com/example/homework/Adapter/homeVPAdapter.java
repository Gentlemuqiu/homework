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
        //创造对象
        ImageView bannerImageView = new ImageView(container.getContext());
        //设置宽高的参数
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bannerImageView.setLayoutParams(lp);
        //设置显示方式
        bannerImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(bannerImageView).load(mBannerList.get(position).getImagePath()).into(bannerImageView);
        //设置监听事件,跳转到webView;
        bannerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), webView.class);
                intent.putExtra("url",mBannerList.get(position).getUrl());
                view.getContext().startActivity(intent);
            }
        });
        //在容器中增加view,可以更快的加载
        container.addView(bannerImageView);
        return bannerImageView;
    }
    //移除view,防止加载过多,带来的卡顿
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setDate(banner banner) {
        //刷新
        mBannerList.clear();
        //放置数据
        mBannerList.addAll(banner.getData());
        //刷新
        notifyDataSetChanged();
    }


}
