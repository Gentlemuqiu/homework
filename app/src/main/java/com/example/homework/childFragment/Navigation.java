package com.example.homework.childFragment;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.NavLeftAdapter;
import com.example.homework.Adapter.NavRightAdapter;
import com.example.homework.R;
import com.example.homework.bean.NavBean;
import com.google.gson.Gson;

public class Navigation extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerViewLeft;
    private NavLeftAdapter mNavLeftAdapter;
    private RecyclerView mRecyclerViewRight;
    private NavRightAdapter mNavRightAdapter;
    public Navigation() {

    }

    public static Navigation newInstance(String param1, String param2) {
        Navigation fragment = new Navigation();
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
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerViewRight = view.findViewById(R.id.rv_right);
        mRecyclerViewLeft = view.findViewById(R.id.rv_left);
        mNavRightAdapter = new NavRightAdapter();
        mNavLeftAdapter = new NavLeftAdapter(mRecyclerViewRight,mNavRightAdapter);
        netLoadNav();
        mRecyclerViewLeft.setAdapter(mNavLeftAdapter);
        mRecyclerViewLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewLeft.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,10,0,10);
            }
        });
        mRecyclerViewLeft.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    private void netLoadNav() {
        String url = "https://www.wanandroid.com/navi/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result=doGet(url);
                Gson gson=new Gson();
                NavBean navBean=gson.fromJson(result,NavBean.class);
                updateUINav(navBean);
            }
        }).start();
    }

    private void updateUINav(NavBean navBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mNavLeftAdapter.setDate(navBean);
            }
        });
    }
}