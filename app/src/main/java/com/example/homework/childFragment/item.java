package com.example.homework.childFragment;

import static com.example.homework.Util.NetWorkGet.doGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Adapter.itemAdapter;
import com.example.homework.R;
import com.example.homework.bean.itemBean;
import com.example.homework.bean.itemChildBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class item extends Fragment implements AdapterView.OnItemSelectedListener {
    private List<itemBean.DataDTO> mList = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<String> mStringList = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private Spinner mSpinner;
    private RecyclerView mMRecyclerView;
    private itemAdapter mItemAdapter;

    public item() {
    }

    public static item newInstance(String param1, String param2) {
        item fragment = new item();
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
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //防止异步加载带来的问题,手动加入第一条
        mStringList.add("请选择项目类别");
        //网络加载
        netLoadItem();
        mSpinner = view.findViewById(R.id.sp_dropdown);
        //设置spinner的适配器
        ArrayAdapter<String> startAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_select, mStringList);
        mSpinner.setAdapter(startAdapter);
        //默认选中第一条
        mSpinner.setSelection(0);
        //设计点击事件监听
        mSpinner.setOnItemSelectedListener(this);
        mMRecyclerView = view.findViewById(R.id.rv_item);
        mMRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void netLoadItem() {
        String url = "https://www.wanandroid.com/project/tree/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doGet(url);
                Gson gson = new Gson();
                itemBean itemBean = gson.fromJson(result, com.example.homework.bean.itemBean.class);
                //返回到主线程
                updateUIItem(itemBean);
            }
        }).start();
    }

    private void updateUIItem(itemBean itemBean) {
        //防止尚未加载完成,就切换到另一个页面而造成的闪退
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //为Spinner增加数据
                    mList.addAll(itemBean.getData());
                    for (int i = 0; i < mList.size(); i++) {
                        mStringList.add(mList.get(i).getName());
                    }
                }
            });
        }
    }
      //被选中条目后执行的操作
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mItemAdapter = new itemAdapter(getContext());
        //第一条为手动增加的条目,故不做处理,直接消费掉
        if (i == 0) {
            return;
        } else {
            //取得对应条目的id加载 相应条目的数据
            int id = mList.get(i - 1).getId();
            netLoadItemChild(id);
            mMRecyclerView.setAdapter(mItemAdapter);
        }
    }

    private void netLoadItemChild(int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    String url = "https://www.wanandroid.com/project/list/" + i + "/json?cid=" + id;
                    String result = doGet(url);
                    Gson gson = new Gson();
                    itemChildBean itemChildBean = gson.fromJson(result, com.example.homework.bean.itemChildBean.class);
                    //返回主线程
                    upDataUIChildItem(itemChildBean);
                }
            }
        }).start();

    }

    private void upDataUIChildItem(itemChildBean itemChildBean) {
        //防止未加载完数据便切换到另一个界面带来的卡顿
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                //更新数据
                public void run() {
                    mItemAdapter.setData(itemChildBean);
                }
            });
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}