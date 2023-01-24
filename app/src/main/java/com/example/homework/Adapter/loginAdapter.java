package com.example.homework.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.homework.R;
import com.example.homework.login.RegisterActivity;
import com.example.homework.login.loginActivity;

import java.util.ArrayList;
import java.util.List;

public class loginAdapter extends PagerAdapter {
    private List<View> mViewList = new ArrayList<>();

    public loginAdapter(Context context, int[] imageArray) {
        for (int i = 0; i < imageArray.length; i++) {
            //每个页面设置一张图片
            View view = LayoutInflater.from(context).inflate(R.layout.login, null);
            ImageView iv_login = view.findViewById(R.id.iv_login);
            RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
            Button btn_login = view.findViewById(R.id.btn_login);
            Button btn_register = view.findViewById(R.id.btn_register);
            iv_login.setImageResource(imageArray[i]);
            for (int j = 0; j < imageArray.length; j++) {
                //每个图片设4个单选按钮
                RadioButton radioButton = new RadioButton(context);
                rg_indicate.addView(radioButton);
            }
            //翻页变亮
            ((RadioButton) rg_indicate.getChildAt(i)).setChecked(true);
            if (i == imageArray.length - 1) {
                btn_login.setVisibility(View.VISIBLE);
                btn_register.setVisibility(View.VISIBLE);
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, loginActivity.class);
                        context.startActivity(intent);
                    }
                });
                btn_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, RegisterActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
            mViewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View item = mViewList.get(position);
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }
}
