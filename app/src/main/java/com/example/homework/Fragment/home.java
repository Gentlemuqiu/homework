package com.example.homework.Fragment;

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
import androidx.viewpager.widget.ViewPager;

import com.example.homework.Adapter.homeRVAdapter;
import com.example.homework.Adapter.homeVPAdapter;
import com.example.homework.R;
import com.example.homework.bean.banner;
import com.example.homework.bean.homePager;
import com.example.homework.bean.homePagerTop;
import com.google.gson.Gson;

public class home extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ViewPager mViewPager;
    private homeVPAdapter mHomeVPAdapter;
    private RecyclerView mRecyclerView;
    private homeRVAdapter mHomeRVAdapter;
    public home() {
    }

    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = view.findViewById(R.id.vp_home);
        mHomeVPAdapter = new homeVPAdapter();
        netLoadBanner();
        mViewPager.setAdapter(mHomeVPAdapter);
        mRecyclerView=view.findViewById(R.id.rv_home);
        mHomeRVAdapter=new homeRVAdapter();
        netLoadHome();
        netLoadHomeTop();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mHomeRVAdapter);

    }

    private void netLoadHomeTop() {
        String url="https://www.wanandroid.com/article/top/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result=doGet(url);
                Gson gson=new Gson();
                homePagerTop homePagerTop=gson.fromJson(result, homePagerTop.class);
                updateUIHomePagerTop(homePagerTop);
            }
        }).start();
    }

    private void updateUIHomePagerTop(homePagerTop homePagerTop) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHomeRVAdapter.setDate(homePagerTop);
            }
        });
    }


    private void netLoadHome() {
        for (int i=0;i<40;i++) {
            String url="https://www.wanandroid.com/article/list/"+i+"/json";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result=doGet(url);
                    Gson gson=new Gson();
                    homePager homePager=gson.fromJson(result, com.example.homework.bean.homePager.class);
                    updateUIHome(homePager);
                }
            }).start();
        }
    }

    private void updateUIHome(homePager homePager) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHomeRVAdapter.setDate1(homePager);
            }
        });
    }


    private void netLoadBanner() {
        String url = "https://www.wanandroid.com/banner/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doGet(url);
                //跳到主线程
                Gson gson = new Gson();
                banner banner = gson.fromJson(result, banner.class);
                updateUIBanner(banner);
            }
        }).start();
    }


    private void updateUIBanner(banner banner) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHomeVPAdapter.setDate(banner);
            }
        });
    }

}