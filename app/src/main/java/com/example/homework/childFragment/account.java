package com.example.homework.childFragment;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.basicAccountAdapter;
import com.example.homework.R;
import com.example.homework.bean.basicAccountBean;
import com.google.gson.Gson;

public class account extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private basicAccountAdapter mBasicAccountAdapter;

    public account() {

    }

    public static account newInstance(String param1, String param2) {
        account fragment = new account();
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
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_account);
        netLoadAccount();
        mBasicAccountAdapter = new basicAccountAdapter();
        mRecyclerView.setAdapter(mBasicAccountAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, RecyclerView.VERTICAL, false);
        //每5个为一组,每四个后变成大的
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return(1+position) %5 ==0 ?2:1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

    }

    private void netLoadAccount() {
        String url = "https://wanandroid.com/wxarticle/chapters/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doGet(url);
                Gson gson = new Gson();
                basicAccountBean basicAccountBean = gson.fromJson(result, com.example.homework.bean.basicAccountBean.class);
                upAccountDateUI(basicAccountBean);
            }
        }).start();
    }

    private void upAccountDateUI(basicAccountBean basicAccountBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBasicAccountAdapter.setDate(basicAccountBean);
            }
        });
    }
}