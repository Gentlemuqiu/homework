package com.example.homework.childFragment;

import static com.example.homework.Util.NetWorkGet.doGet;
import static com.example.homework.Util.NetWorkPost.sendPost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.AlwaysAdapter;
import com.example.homework.Adapter.hotWordAdapter;
import com.example.homework.Adapter.searchAdapter;
import com.example.homework.R;
import com.example.homework.bean.alwaysBean;
import com.example.homework.bean.hotWordBean;
import com.example.homework.bean.searchBean;
import com.google.gson.Gson;

public class search extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView rv_always, rv_hotWord, rv_search;
    private AlwaysAdapter mAlwaysAdapter;
    private hotWordAdapter mHotWordAdapter;
    private searchAdapter mSearchAdapter;
    private SearchView mSearchView;
    private LinearLayout mLinearLayout;


    public search() {
    }

    public static search newInstance(String param1, String param2) {
        search fragment = new search();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
        mSearchView = view.findViewById(R.id.sv_search);
        rv_search = view.findViewById(R.id.rv_search);
        mLinearLayout = view.findViewById(R.id.ll_text);
        mSearchAdapter = new searchAdapter();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //得到查询的名称放入接口中
                upLoadSearchNet(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //判断文本是否为空, 空的话显示热词和常用文章,不为空则隐藏
                if (newText.equals("")) {
                    searchAdapter.mList.clear();
                    rv_search.setVisibility(View.GONE);
                    rv_always.setVisibility(View.VISIBLE);
                    rv_hotWord.setVisibility(View.VISIBLE);
                    mLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    rv_search.setVisibility(View.VISIBLE);
                    rv_always.setVisibility(View.GONE);
                    rv_hotWord.setVisibility(View.GONE);
                    mLinearLayout.setVisibility(View.GONE);
                }
                return true;
            }
        });
        rv_search.setAdapter(mSearchAdapter);
        rv_search.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_search.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void upLoadSearchNet(String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    String url = "https://www.wanandroid.com/article/query/" + i + "/json?k=" + query;
                    String result = sendPost(url);
                    Gson gson = new Gson();
                    searchBean searchBean = gson.fromJson(result, com.example.homework.bean.searchBean.class);
                    //跳到主线程
                    upDateUISearch(searchBean);
                }
            }
        }).start();
    }

    private void upDateUISearch(searchBean searchBean) {
        //防止未加载完全,划入另一个界面导致闪退
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mSearchAdapter.setData(searchBean);
                }
            });
        }
    }

    private void initRecyclerView(View view) {
        rv_always = view.findViewById(R.id.rv_always);
        rv_hotWord = view.findViewById(R.id.rv_hotWord);
        mAlwaysAdapter = new AlwaysAdapter();
        mHotWordAdapter = new hotWordAdapter();
        upLoadAlwaysNet();
        upLoadHotWordNet();
        rv_always.setAdapter(mAlwaysAdapter);
        rv_always.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_hotWord.setAdapter(mHotWordAdapter);
        rv_hotWord.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //加载
    private void upLoadHotWordNet() {
        String url = "https://www.wanandroid.com//hotkey/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doGet(url);
                Gson gson = new Gson();
                hotWordBean hotWordBean = gson.fromJson(result, com.example.homework.bean.hotWordBean.class);
                //更新主线程
                upLoadUIHotWordNet(hotWordBean);
            }
        }).start();
    }

    private void upLoadUIHotWordNet(hotWordBean hotWordBean) {
        //防止未加载完全,划入另一个界面导致闪退
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mHotWordAdapter.setData(hotWordBean);
                }
            });
        }
    }

    //加载
    private void upLoadAlwaysNet() {
        String url = "https://www.wanandroid.com/friend/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doGet(url);
                Gson gson = new Gson();
                alwaysBean alwaysBean = gson.fromJson(result, com.example.homework.bean.alwaysBean.class);
                //更新回主界面
                upLoadUIAlways(alwaysBean);
            }
        }).start();
    }

    private void upLoadUIAlways(alwaysBean alwaysBean) {
        //防止未加载完全,划入另一个界面导致闪退
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAlwaysAdapter.setDate(alwaysBean);
                }
            });
        }
    }
}