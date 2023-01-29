package com.example.homework.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.loveAdapter;
import com.example.homework.R;
import com.example.homework.bean.homePager;
import com.example.homework.childActivity.addLove;
import com.example.homework.login.loginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class love extends Fragment {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private loveAdapter mLoveAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .build();

    public love() {
    }

    public static love newInstance(String param1, String param2) {
        love fragment = new love();
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
        return inflater.inflate(R.layout.fragment_love, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_love);
        mBtnAdd=view.findViewById(R.id.fab_plus);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), addLove.class);
                view.getContext().startActivity(intent);
            }
        });
        upNetLoad();
        mLoveAdapter = new loveAdapter();
    }

    private void upNetLoad() {

            String url = "https://www.wanandroid.com/lg/collect/list/" + 0 + "/json";
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .addHeader("Cookie", String.valueOf(loginActivity.cookies))
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    homePager homePager = gson.fromJson(result, com.example.homework.bean.homePager.class);
                    Log.d("hui", "onResponse: " + result);
                    upUI(homePager);
                }
            });
        }

    private void upUI(homePager homePager) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLoveAdapter.setData(homePager);
                updateUI();
            }
        }).start();
    }

    private void updateUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setAdapter(mLoveAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

    }



/*
     {
        for (int i = 0; i < 40; i++) {
            String url = "https://www.wanandroid.com/lg/collect/list/" + i + "/json";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = doGet(url);
                    Gson gson = new Gson();
                    homePager homePager = gson.fromJson(result, com.example.homework.bean.homePager.class);
                    Log.d("hui", "run: "+result);
                    upDateUI(homePager);
                }
            }).start();
        }
    }

    private void upDateUI(homePager homePager) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLoveAdapter.setData(homePager);
            }
        });
    }*/
}