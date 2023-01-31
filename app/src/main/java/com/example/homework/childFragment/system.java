package com.example.homework.childFragment;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.systemAdapter;
import com.example.homework.R;
import com.example.homework.bean.systemBean;
import com.google.gson.Gson;

public class system extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mMRecyclerView;
    private systemAdapter mSystemAdapter;

    public system() {
    }

    public static system newInstance(String param1, String param2) {
        system fragment = new system();
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
        return inflater.inflate(R.layout.fragment_system, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMRecyclerView = view.findViewById(R.id.rv_ground);
        mSystemAdapter = new systemAdapter();
        //网络加载
        netLoadTitle();
        mMRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMRecyclerView.setAdapter(mSystemAdapter);

    }

    private void netLoadTitle() {
        String url="https://www.wanandroid.com/tree/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result=doGet(url);
                Gson gson=new Gson();
                systemBean systembean=gson.fromJson(result, systemBean.class);
                //切换到主线程
                updateUISystemTitle(systembean);
            }
        }).start();
    }

    private void updateUISystemTitle(systemBean systembean) {
    getActivity().runOnUiThread(new Runnable() {
        @Override
        //更新数据
        public void run() {
            mSystemAdapter.setDate(systembean);
        }
    });
    }
}