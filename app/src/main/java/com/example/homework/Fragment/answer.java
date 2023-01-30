package com.example.homework.Fragment;

import static com.example.homework.Util.NetWorkGet.doCookieGet;

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

import com.example.homework.Adapter.answerAdapter;
import com.example.homework.R;
import com.example.homework.bean.answerBean;
import com.google.gson.Gson;

public class answer extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private answerAdapter mAnswerAdapter;

    public answer() {
    }

    public static answer newInstance(String param1, String param2) {
        answer fragment = new answer();
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
        return inflater.inflate(R.layout.fragment_answer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_answer);
        //加载
        NetLoad();
        mAnswerAdapter = new answerAdapter();
        mRecyclerView.setAdapter(mAnswerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void NetLoad() {
        for (int i = 0; i < 10; i++) {
            String url = "https://wanandroid.com/wenda/list/" + i + "/json ";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = doCookieGet(url);
                    Log.d("hui", "run: " + result);
                    //利用gson拿到bean对象
                    Gson gson = new Gson();
                    answerBean answerBean = gson.fromJson(result, com.example.homework.bean.answerBean.class);
                    //在主线程中更新数据
                    upDataUI(answerBean);
                }
            }).start();
        }
    }

    private void upDataUI(answerBean answerBean) {
        //当处于这个页面时调用主线程
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //更新数据
                    mAnswerAdapter.setData(answerBean);
                }
            });
        }
    }
}